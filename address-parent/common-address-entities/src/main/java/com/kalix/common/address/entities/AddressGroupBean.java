package com.kalix.common.address.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述： 通讯录实体类
 * @创建人： 杨泽
 * @创建时间： 2018-3-2
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "common_address_group")
//@IdClass(AddressGroupPK.class)
public class AddressGroupBean extends PersistentEntity {
//    @Id
    private String userId;
//    @Id
    private String groupName;
    private String describe;
    private Boolean isDefault;
    private String iconCls;

    public AddressGroupBean() {
        super();
    }

    public AddressGroupBean(String userId, String groupName) {
        super();
        this.userId = userId;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
}
