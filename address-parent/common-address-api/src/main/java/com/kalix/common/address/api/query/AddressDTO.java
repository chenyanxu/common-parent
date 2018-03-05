package com.kalix.common.address.api.query;


import com.kalix.framework.core.api.web.model.QueryDTO;

/**
 * Created by sunlf on 2015/11/5.
 */
public class AddressDTO extends QueryDTO {
    private String name; // 姓名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}