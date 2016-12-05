package com.kalix.common.message.biz.xmppevent;

import com.kalix.common.message.biz.MessageServcieImpl;
import org.osgi.service.event.Event;

/**
 * Created by Administrator on 2016/12/5.
 */
public class ScheduleNewMsgEventImpl extends MessageServcieImpl {
    public void handleEvent(Event event) {
        this.setMessageType(MessageType.SCHEDULE_NEW);
        super.handleEvent(event);
    }
}
