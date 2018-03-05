package com.kalix.common.address.entities;

import java.io.Serializable;

public class AddressGroupPK implements Serializable {
    private Long userId;
    private String groupName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
