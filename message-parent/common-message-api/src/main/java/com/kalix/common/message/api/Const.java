package com.kalix.common.message.api;

/**
 * Created by sunlf on 2016/2/26.
 */
public class Const {
    //轮询系统消息topic
    public static final String POLLING_MESSAGE_TOPIC_FORMAT = "common.message.%s";
    //个人消息topic
    public static final String COMMON_MESSAGE_TOPIC = "com/kalix/common/message";
    //计划任务 新消息topic
    public static final String SCHEDULE_ASSIGNMENT_NEW_TOPIC = "com/kalix/schedule/message/new";
    //计划任务 状态修改消息topic
    public static final String SCHEDULE_ASSIGNMENT_CHANGE_TOPIC = "com/kalix/schedule/message/change";
}
