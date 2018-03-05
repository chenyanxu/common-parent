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
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.SerializeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @类描述：
 * @创建人：
 * @创建时间：
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class AddressGroupBeanServiceImpl extends GenericBizServiceImpl<IAddressGroupBeanDao, AddressGroupBean> implements IAddressGroupBeanService {
    private IOrganizationBeanService orgBeanService;
    private IUserBeanService userBeanService;
    private IAddressBeanService addressBeanService;

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
        Map<String, String> jsonMap = SerializeUtil.json2Map(jsonStr);
        String userId = jsonMap.get("userId");
        Long currentUserId = Long.parseLong(userId);
        // 初始化我的同事分组
        initMyWorkmateGroup(currentUserId, "我的同事");
        // 初始化我的好友分组
        initMyFriendGroup(currentUserId, "我的好友");
        List<AddressGroupBean> groups = dao.getAllGroupsByUser(currentUserId);
        JsonData jsonData = new JsonData();
        jsonData.setData(groups);
        return jsonData;
    }

    private void initMyFriendGroup(Long currentUserId, String friendGroupName) {
        AddressGroupBean myFriendGroup = dao.getGroupByName(currentUserId, friendGroupName);
        if (myFriendGroup == null) {
            initMyGroup(currentUserId, friendGroupName);
        }
    }

    private void initMyWorkmateGroup(Long currentUserId, String workmateGroupName) {
        AddressGroupBean myWorkMateGroup = dao.getGroupByName(currentUserId, workmateGroupName);
        if (myWorkMateGroup == null) {
            AddressGroupBean group = initMyGroup(currentUserId, workmateGroupName);
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
                    addressBeanService.saveEntity(addressBean);
                }
            }
        }
    }

    private List<UserBean> getMyWorkmateInfos(Long currentUserId) {
        List<Long> orgIds = orgBeanService.getOrgsByUserId(currentUserId, false);
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

    private AddressGroupBean initMyGroup(Long userId, String groupName) {
        AddressGroupBean myGroup = new AddressGroupBean();
        myGroup.setUserId(userId);
        myGroup.setGroupName(groupName);
        dao.save(myGroup);
        return myGroup;
    }
}
