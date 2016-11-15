package com.kalix.common.message.entities;


import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：消息管理
 * @创建人：zangyanming
 * @创建时间：2016-02-19
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "common_sendermsg")
public class SenderMessageBean extends PersistentEntity {
    /**
     * @describe 发送者
     * @validator 不能为空
     */
    private Long senderId;
    /**
     * @describe 接收者与用户表关联的主键id
     * @validator 不能为空
     */
    private String receiverIds;
    /**
     * @describe 接收者
     * @validator 不能为空
     */
    private String receiverNames;
    /**
     * @describe 消息类别
     * @validator 不能为空
     */
    private String category;
    /**
     * @describe 消息标题
     * @validator 不能为空
     */
    private String title;
    /**
     * @describe 消息内容
     * @validator 不能为空
     */
    private String content;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

    public String getReceiverNames() {
        return receiverNames;
    }

    public void setReceiverNames(String receiverNames) {
        this.receiverNames = receiverNames;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
