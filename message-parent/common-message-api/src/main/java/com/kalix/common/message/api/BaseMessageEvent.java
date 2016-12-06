package com.kalix.common.message.api;

import com.kalix.admin.core.api.biz.IUserBeanService;

/**
 * Created by sunlf on 2016/2/26.
 * 消息处理抽象类
 */
public abstract class BaseMessageEvent {

    protected IUserBeanService userBeanService;

    public void setUserBeanService(IUserBeanService userBeanService) {
        this.userBeanService = userBeanService;
    }
}
