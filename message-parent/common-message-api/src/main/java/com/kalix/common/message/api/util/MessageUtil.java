package com.kalix.common.message.api.util;

import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.admin.duty.api.biz.IDutyBeanService;
import com.kalix.framework.core.util.OsgiUtil;
import org.activiti.engine.impl.util.json.JSONObject;
import org.osgi.service.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houqj on 2016/12/5.
 */
public class MessageUtil {

    static final String MSG_CONTENT_COMMON = "%s,您好！\r\n  您收到了一条新信息，请查看！";
    static final String MSG_CONTENT_SCHEDULE_NEW = "%s,您好！\r\n  您收到了一条由%s布置的新任务，任务名称为:%s，请查看！";
    static final String MSG_CONTENT_SCHEDULE_CHANGE = "%s,您好！\r\n  您布置的任务[%s]状态已经由[%s]修改,状态从[%s]修改为:[%s]，请查看！";
    static final String MSG_CONTENT_SCHEDULE_SUPERVISE = "%s,您好！\r\n  您收到了一条由%s督办的消息，任务名称为:%s，%s！";
    static final String MSG_CONTENT_WORKFLOW_NEW = "%s,您好！\r\n  您有一个待办流程请尽快处理！流程号为[%s]。";
    static final String MSG_CONTENT_WORKFLOW_PROGRESS = "%s,您好！\r\n  您流程编号为[%s]的申请已经审批，请查看！";

    static IUserBeanService userBeanService = OsgiUtil.waitForServices(IUserBeanService.class,null);
    static IDutyBeanService dutyBeanService = OsgiUtil.waitForServices(IDutyBeanService.class,null);

    public static Map<Long,String> getCommonMessage(Event event) {

        Map<Long, String> msgs = new HashMap<Long, String>();

        Long userId = (Long) event.getProperty("userId");
        String receiverName = getUserName(userId);
        String msg = formatCommonMessage(receiverName);
        msgs.put(userId, msg);

        return msgs;
    }

    public static Map<Long,String> getScheduleNewMessage(Event event) {

        Map<Long, String> msgs = new HashMap<Long, String>();

        //布置人
        String userName = (String) event.getProperty("userName");
        //任务名称
        String taskName = (String) event.getProperty("taskName");

        //负责人
        Long head = (Long) event.getProperty("head");
        String receiverName = getUserName(head);
        String msg = formatScheduleNewMessage(receiverName, userName, taskName);
        msgs.put(head, msg);

        //参与人
        String participant = (String) event.getProperty("participant");
        String[] participants = participant.split(",");
        if (participants != null && participants.length > 0) {
            for (int i = 0; i < participants.length; i++) {
                if (participants[i] != null && !participants[i].isEmpty()) {
                    Long uid = Long.parseLong(participants[i]);
                    receiverName = getUserName(uid);
                    msg = formatScheduleNewMessage(receiverName, userName, taskName);
                    msgs.put(uid, msg);
                }
            }
        }

        return  msgs;
    }

    public static Map<Long,String> getScheduleChangeMessage(Event event) {

        Map<Long, String> msgs = new HashMap<Long, String>();

        //布置人
        String userName = (String) event.getProperty("userName");
        //任务名称
        String taskName = (String) event.getProperty("taskName");
        //负责人
        Long head = (Long) event.getProperty("head");
        String senderName = getUserName(head);
        //任务旧状态
        String oldState = (String) event.getProperty("oldState");
        //任务状态
        String state = (String) event.getProperty("state");
        String msg = formatScheduleChangeMessage(userName, taskName, senderName, oldState, state);
        msgs.put(head, msg);

        return msgs;
    }

    public static Map<Long,String> getScheduleSuperviseMessage(Event event) {

        Map<Long, String> msgs = new HashMap<Long, String>();

        //负责人
        Long head = (Long) event.getProperty("head");
        String receiverName = getUserName(head);
        //布置人
        String userName = (String) event.getProperty("userName");
        //任务名称
        String taskName = (String) event.getProperty("taskName");
        String info = (String) event.getProperty("info");
        String msg = formatScheduleSuperviseMessage(receiverName, userName, taskName, info);
        msgs.put(head, msg);

        return msgs;
    }

    public static Map<Long,String> getWorkFlowNewTaskMessage(Event event) {

        Map<Long, String> msgs = new HashMap<Long, String>();

        String json = (String) event.getProperty("body");
        JSONObject taskJson = new JSONObject(json);
        String businessNo = (String) taskJson.get("businessNo");
        //查找组对应的用户id
        String groupName = (String) taskJson.get("group");
        if (!groupName.equals("")) {
            //解析group，orgName+“-”+职位
            String str[] = groupName.split("::");
            String orgName = str[0];
            String dutyName = str[1];
            List<String> loginUserList = getUserList(orgName, dutyName);
            for (String userloginName : loginUserList) {
                UserBean userBean = userBeanService.getUserBeanByLoginName(userloginName);
                String content = formatWorkFlowNewTaskMessage(userBean.getName(), businessNo);
                msgs.put(userBean.getId(), content);
            }
        }
        String userId = (String) taskJson.get("userId");
        if (!userId.equals("")) {
            UserBean userBean = userBeanService.getUserBeanByLoginName(userId);
            String content = formatWorkFlowNewTaskMessage(userBean.getName(), businessNo);
            msgs.put(userBean.getId(), content);
        }

        return  msgs;
    }

    public static Map<Long,String> getWorkFlowProgressMessage(Event event) {

        Map<Long, String> msgs = new HashMap<Long, String>();

        String json = (String) event.getProperty("body");
        JSONObject taskJson = new JSONObject(json);
        String userId = (String) taskJson.get("startUserId");
        UserBean userBean = userBeanService.getUserBeanByLoginName(userId);
        String businessNo = (String) taskJson.get("businessNo");
        String msg = formatWorkFlowProgressMessage(userId, businessNo);
        msgs.put(userBean.getId(), msg);

        return msgs;
    }

    protected static String formatCommonMessage(String receiverName) {
        return String.format(MSG_CONTENT_COMMON, receiverName);
    }

    protected static String formatScheduleNewMessage(String receiverName, String senderName, String taskName) {
        return String.format(MSG_CONTENT_SCHEDULE_NEW, receiverName, senderName, taskName);
    }

    protected static String formatScheduleChangeMessage(
            String receiverName, String taskName, String senderName, String oldState, String state) {
        return String.format(MSG_CONTENT_SCHEDULE_CHANGE, receiverName, taskName, senderName, oldState, state);
    }

    protected static String formatScheduleSuperviseMessage(
            String receiverName, String senderName, String taskName, String info) {
        return String.format(MSG_CONTENT_SCHEDULE_SUPERVISE, receiverName, senderName, taskName, info);
    }

    protected static String formatWorkFlowNewTaskMessage(String receiverName, String businessNo) {
        return String.format(MSG_CONTENT_WORKFLOW_NEW, receiverName, businessNo);
    }

    protected static String formatWorkFlowProgressMessage(String receiverName, String businessNo) {
        return String.format(MSG_CONTENT_WORKFLOW_PROGRESS, receiverName, businessNo);
    }

    protected static String getUserName(Long userId) {

        String userName = "";
        try {
            UserBean userBean = userBeanService.getEntity(userId);
            userName = userBean.getName();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    protected static List<String> getUserList(String orgName, String dutyName) {
        List<String> userList = new ArrayList<String>();
        try {
            userList = dutyBeanService.getUserListByOrgName(orgName, dutyName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
