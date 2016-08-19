package com.kalix.common.message.biz.system;


import com.kalix.framework.core.api.system.IPollingService;

/**
 * Created by zangyanming on 2016/2/25.
 */
public class MessagePollingServiceImpl implements IPollingService {
    private String id;
    private String type;
    private int interval;
    private String url;
    private boolean stop;
    private String callbackHandler;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getInterval() {
        return interval;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public boolean isStop() {
        return stop;
    }

    @Override
    public String getCallbackHandler() {
        return callbackHandler;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setCallbackHandler(String callbackHandler) {
        this.callbackHandler = callbackHandler;
    }
}
