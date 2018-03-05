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
@Table(name = "common_address")
public class AddressBean extends PersistentEntity {
    private Long userId; // 用户ID
    private Long addressUserId; // 通讯录用户ID
    private String name; // 姓名
    private String nickName; // 昵称
    private String icon; // 头像
    private String phone; // 联系电话
    private String email; // 邮箱
    private String qqNum; // QQ号
    private String sex; // 性别
    private String address; // 通讯地址
    private String remark; // 备注
    private Long groupId; // 分组ID

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getAddressUserId() {
        return addressUserId;
    }

    public void setAddressUserId(Long addressUserId) {
        this.addressUserId = addressUserId;
    }
}
