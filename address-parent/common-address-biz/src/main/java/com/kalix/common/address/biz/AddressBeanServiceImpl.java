package com.kalix.common.address.biz;

import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.address.api.biz.IAddressBeanService;
import com.kalix.common.address.api.dao.IAddressBeanDao;
import com.kalix.common.address.entities.AddressBean;
import com.kalix.common.message.api.MessageCategories;
import com.kalix.common.message.api.biz.IMessageBeanService;
import com.kalix.common.message.api.biz.ISenderMessageBeanService;
import com.kalix.common.message.entities.MessageBean;
import com.kalix.common.message.entities.SenderMessageBean;
import com.kalix.common.system.dict.api.biz.ICommonDictBeanService;
import com.kalix.common.system.dict.entities.CommonDictBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.security.IShiroService;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.SerializeUtil;
import org.apache.commons.lang.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AddressBeanServiceImpl extends GenericBizServiceImpl<IAddressBeanDao, AddressBean> implements IAddressBeanService {
    private ISenderMessageBeanService senderMessageService;
    private ICommonDictBeanService commonDictBeanService;
    private IUserBeanService userBeanService;
    private IMessageBeanService messageBeanService;

    public void setSenderMessageService(ISenderMessageBeanService senderMessageService) {
        this.senderMessageService = senderMessageService;
    }

    public void setCommonDictBeanService(ICommonDictBeanService commonDictBeanService) {
        this.commonDictBeanService = commonDictBeanService;
    }

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }

    public void setMessageBeanService(IMessageBeanService messageBeanService) {
        this.messageBeanService = messageBeanService;
    }

    public AddressBeanServiceImpl() {
        super.init(AddressBean.class.getName());
    }

    @Override
    public JsonData getDefaultAddressList(String jsonStr, String otherStr) {
        // search form下的查询条件
        Map<String, String> jsonMap = SerializeUtil.json2Map(jsonStr);
        String name = jsonMap.get("%name%");
        // 其他的查询条件
//        Map<String, String> jsonOtherMap = SerializeUtil.json2Map(otherStr);
        String userIdTemp = jsonMap.get("userId");
        String groupIdTemp = jsonMap.get("groupId");
        Long userId = null;
        if (userIdTemp != null) {
            userId = Long.parseLong(userIdTemp);
        }
        Long groupId = null;
        if (groupIdTemp != null) {
            groupId = Long.parseLong(groupIdTemp);
        }
        List<AddressBean> addressList = dao.getDefaultAddresses(userId, groupId, name);
        JsonData jsonData = new JsonData();
        if (addressList != null && !addressList.isEmpty()) {
            jsonData.setTotalCount((long)addressList.size());
        } else {
            jsonData.setTotalCount(0L);
        }
        jsonData.setData(addressList);
        return jsonData;
    }

    @Override
    public void changeToDefaultGroup(Long userId, Long oldGroupId, Long newGroupId) {
        dao.changeAddressGroup(userId, oldGroupId, newGroupId);
    }

    public void deleteAddressByGroup(Long groupId) {
        dao.deleteByGroupId(groupId);
    }

    @Override
    public List<AddressBean> getAddressListByGroup(Long userId, Long groupId) {
        return dao.getDefaultAddresses(userId, groupId, null);
    }

    @Override
    public JsonStatus saveNewAddress(AddressBean entity) {
        Long groupId = entity.getGroupId();
        Long addressUserId = entity.getAddressUserId();
        AddressBean address = dao.getAddressByGroupAndUser(groupId, addressUserId);
        JsonStatus jsonStatus = new JsonStatus();
        try {
            if (address != null) {
                updateEntity(address);
                sendAddFriendNotice(address);
                setJsonStatus(jsonStatus, "", "操作成功", true);
                return jsonStatus;
            }
            saveEntity(entity);
            sendAddFriendNotice(entity);
            setJsonStatus(jsonStatus, "", "操作成功", true);
            return jsonStatus;
        } catch(Exception e) {
            e.printStackTrace();
            setJsonStatus(jsonStatus, "", "操作失败", false);
            return jsonStatus;
        }
    }

    @Override
    public JsonStatus agreeAddFriend(MessageBean entity) {
        JsonStatus jsonStatus = new JsonStatus();
        if (entity == null) {
            setJsonStatus(jsonStatus, "", "操作失败", false);
            return jsonStatus;
        }
        try {
            dao.updateAddressAgree(entity.getReceiverId());
            messageBeanService.deleteEntity(entity.getId());
            replyAddFriendNotice(entity);
            setJsonStatus(jsonStatus, "", "操作成功", true);
        } catch(Exception e) {
            e.printStackTrace();
            setJsonStatus(jsonStatus, "", "操作失败", false);
        }
        return jsonStatus;
    }

    @Override
    public JsonStatus degreeAddFriend(MessageBean entity) {
        JsonStatus jsonStatus = new JsonStatus();
        if (entity == null) {
            setJsonStatus(jsonStatus, "", "操作失败", false);
            return jsonStatus;
        }
        try {
            dao.deleteAddressByGroupAndUser(entity.getReceiverId());
            messageBeanService.deleteEntity(entity.getId());
//            replyAddFriendNotice(entity, "拒绝添加您为好友");
            setJsonStatus(jsonStatus, "", "操作成功", true);
        } catch(Exception e) {
            e.printStackTrace();
            setJsonStatus(jsonStatus, "", "操作失败", false);
        }
        return jsonStatus;
    }

    private void setJsonStatus(JsonStatus jsonStatus, String tag, String msg, Boolean isSuccess) {
        jsonStatus.setTag(tag);
        jsonStatus.setMsg(msg);
        jsonStatus.setSuccess(isSuccess);
    }

    @Override
    public JsonData getAddressUsers(String userId) {
        Long currentUserId = Long.parseLong(userId);
        List<AddressBean> addressBeans = dao.getDefaultAddresses(currentUserId, null, null);
        List<Long> addressUserIds = new ArrayList<>();
        if (addressBeans != null) {
            addressUserIds = addressBeans.stream().map((item)->item.getAddressUserId()).collect(Collectors.toList());
        }
        JsonData jsonData = new JsonData();
        if (addressUserIds == null || addressUserIds.isEmpty()) {
            jsonData.setTotalCount(0L);
        } else {
            jsonData.setTotalCount((long)addressUserIds.size());
        }
        jsonData.setData(addressUserIds);
        return jsonData;
    }

    @Transactional
    private void replyAddFriendNotice(MessageBean entity) {
        UserBean currentUser = userBeanService.getEntity(entity.getReceiverId());
        SenderMessageBean senderMessageBean = new SenderMessageBean();
        senderMessageBean.setTitle("好友添加回复");
        senderMessageBean.setContent(currentUser.getName() + "同意添加您为好友");
        int category = MessageCategories.COMMON.getId();
        senderMessageBean.setCategory(String.valueOf(category));
        senderMessageBean.setReceiverIds(entity.getSenderId().toString());
        senderMessageBean.setReceiverNames(entity.getSenderName());
        senderMessageService.saveInstantMessage(senderMessageBean);
    }

    @Transactional
    private void sendAddFriendNotice(AddressBean entity) {
        UserBean currentUser = userBeanService.getEntity(entity.getUserId());
        SenderMessageBean senderMessageBean = new SenderMessageBean();
        senderMessageBean.setTitle("好友添加");
        senderMessageBean.setContent(currentUser.getName() + "请求添加为好友");
        int category = MessageCategories.INSTANTMESSAGE.getId();
        senderMessageBean.setCategory(String.valueOf(category));
        senderMessageBean.setReceiverIds(entity.getAddressUserId().toString());
        senderMessageBean.setReceiverNames(entity.getName());
        senderMessageService.saveInstantMessage(senderMessageBean);
    }
}
