package com.kalix.common.address.entities;

import java.io.Serializable;

public class AddressGroupPK implements Serializable {
    private String userId;
    private String groupName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
