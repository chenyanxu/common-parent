package com.kalix.common.address.biz;

import com.kalix.admin.core.api.biz.IOrganizationBeanService;
import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.common.address.api.biz.IAddressBeanService;
import com.kalix.common.address.api.biz.IAddressGroupBeanService;
import com.kalix.common.address.api.dao.IAddressGroupBeanDao;
import com.kalix.common.address.entities.AddressBean;
import com.kalix.common.address.entities.AddressGroupBean;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.SerializeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @类描述：
 * @创建人： yangz
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AddressGroupBeanServiceImpl extends GenericBizServiceImpl<IAddressGroupBeanDao, AddressGroupBean> implements IAddressGroupBeanService {
    private IOrganizationBeanService orgBeanService;
    private IUserBeanService userBeanService;
    private IAddressBeanService addressBeanService;
    private String groupName_MyFriend = "我的好友";
    private String groupName_MyWorkmate = "我的同事";

    public void setOrgBeanService(IOrganizationBeanService orgBeanService) {
        this.orgBeanService = orgBeanService;
    }

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }

    public void setAddressBeanService(IAddressBeanService addressBeanService) {
        this.addressBeanService = addressBeanService;
    }

    public AddressGroupBeanServiceImpl() {
        super.init(AddressGroupBean.class.getName());
    }

    @Override
    public JsonData getAllGroups(String jsonStr) {
        Long currentUserId = getCurrentUserId(jsonStr);
        // 初始化我的好友分组
        initMyFriendGroup(currentUserId, groupName_MyFriend);
        // 初始化我的同事分组
        initMyWorkmateGroup(currentUserId, groupName_MyWorkmate);
        List<AddressGroupBean> groups = dao.getAllGroupsByUser(currentUserId);
        JsonData jsonData = new JsonData();
        jsonData.setData(groups);
        if (groups == null || groups.isEmpty()) {
            jsonData.setTotalCount(0L);
        } else {
            jsonData.setTotalCount((long)groups.size());
        }
        return jsonData;
    }

    @Override
    public JsonStatus deleteGroup(Long id, String jsonStr) {
        Long currentUserId = getCurrentUserId(jsonStr);
        AddressGroupBean myFriendGroup = dao.getGroupByName(currentUserId, "我的好友");
        addressBeanService.changeToDefaultGroup(currentUserId, id, myFriendGroup.getId());
//        addressBeanService.deleteAddressByGroup(id);
        return this.deleteEntity(id);
    }

    private List<AddressBean> getMyGroupList(Long currentUserId, String groupName) {
        AddressGroupBean myGroup = dao.getGroupByName(currentUserId, groupName);
        List<AddressBean> myFriendAddresses = null;
        if (myGroup != null) {
            myFriendAddresses = addressBeanService.getAddressListByGroup(currentUserId, myGroup.getId());
        } else {
            myFriendAddresses = new ArrayList<>();
        }
        return myFriendAddresses;
    }

    private JsonData getJsonData(List<AddressBean> myAddresses) {
        JsonData jsonData = new JsonData();
        if (myAddresses == null) {
            jsonData.setTotalCount(0L);
            myAddresses = new ArrayList<>();
        } else {
            jsonData.setTotalCount((long)myAddresses.size());
        }
        jsonData.setData(myAddresses);
        return jsonData;
    }

    @Override
    public JsonData getMyFriendAddresses(Long currentUserId) {
        List<AddressBean> myFriendAddresses = getMyGroupList(currentUserId, groupName_MyFriend);
        return getJsonData(myFriendAddresses);
    }

    @Override
    public JsonData getMyWorkmateAddresses(Long currentUserId) {
        List<AddressBean> myWorkmateAddresses = getMyGroupList(currentUserId, groupName_MyFriend);
        return getJsonData(myWorkmateAddresses);
    }

    @Override
    public JsonData getMyDefaultGroupAddresses(Long currentUserId) {
        List<AddressGroupBean> defaultGroups = dao.getAllGroupsByDefault(currentUserId);
        List<AddressBean> addressBeans = new ArrayList<>();
        if (defaultGroups != null) {
            for (AddressGroupBean myGroup : defaultGroups) {
                List<AddressBean> addresses = addressBeanService.getAddressListByGroup(currentUserId, myGroup.getId());
                if (addresses != null) {
                    addressBeans.addAll(addresses);
                }
            }
        }
        return getJsonData(addressBeans);
    }

    private void initMyFriendGroup(Long currentUserId, String friendGroupName) {
        AddressGroupBean myFriendGroup = dao.getGroupByName(currentUserId, friendGroupName);
        if (myFriendGroup == null) {
            initMyGroup(currentUserId, friendGroupName, true, "iconfont icon-wodehaoyou");
        }
    }

    private void initMyWorkmateGroup(Long currentUserId, String workmateGroupName) {
        AddressGroupBean myWorkMateGroup = dao.getGroupByName(currentUserId, workmateGroupName);
        if (myWorkMateGroup == null) {
            AddressGroupBean group = initMyGroup(currentUserId, workmateGroupName, true, "iconfont icon-yaoqingtongshi");
            // 初始化我的同事通讯列表
            List<UserBean> users = getMyWorkmateInfos(currentUserId);
            if (users != null && !users.isEmpty()) {
                for (UserBean user : users) {
                    AddressBean addressBean = new AddressBean();
                    addressBean.setUserId(currentUserId);
                    addressBean.setAddressUserId(user.getId());
                    addressBean.setGroupId(group.getId());
                    addressBean.setName(user.getName());
                    addressBean.setNickName(user.getName());
                    addressBean.setEmail(user.getEmail());
                    addressBean.setIcon(user.getIcon());
                    addressBean.setPhone(user.getPhone());
                    addressBean.setAgree(true);
                    addressBeanService.saveEntity(addressBean);
                }
            }
        }
    }

    private List<UserBean> getMyWorkmateInfos(Long currentUserId) {
        List<Long> orgIds = orgBeanService.getOrgsByUserId(currentUserId, false);
        if (orgIds == null || orgIds.isEmpty()) {
            return null;
        }
        List<String> userIdList = new ArrayList<>();
        for (Long orgId : orgIds) {
            List<Long> userIds = orgBeanService.getUserIdsByOrganizationId(orgId);
            for (Long uId : userIds) {
                if (uId != currentUserId && !userIdList.contains(uId)) {
                    userIdList.add(uId.toString());
                }
            }
        }
        return userBeanService.getUsersByIds(userIdList);
    }

    private AddressGroupBean initMyGroup(Long userId, String groupName, Boolean isDefault, String iconCls) {
        AddressGroupBean myGroup = new AddressGroupBean();
        myGroup.setUserId(userId);
        myGroup.setGroupName(groupName);
        myGroup.setDefault(isDefault);
        myGroup.setIconCls(iconCls);
        dao.save(myGroup);
        return myGroup;
    }

    private Long getCurrentUserId(String jsonStr) {
        Map<String, String> jsonMap = SerializeUtil.json2Map(jsonStr);
        String userId = jsonMap.get("userId");
        return Long.parseLong(userId);
    }

}
