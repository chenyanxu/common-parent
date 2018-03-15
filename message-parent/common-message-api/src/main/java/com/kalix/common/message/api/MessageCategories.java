package com.kalix.common.message.api;

/**
 * Created by zangyanming on 2016/3/3.
 */
public enum MessageCategories {
    SYSTEM("系统消息", 0), WORKFLOW("流程消息", 1), COMMON("个人消息", 2),
        INSTANTMESSAGE("即时通讯消息", 3);
//    ASSIGNMENT("计划任务消息", 3);
    // 成员变量
    private String CategoryName;
    private int id;

    // 构造方法
    private MessageCategories(String name, int id) {
        this.CategoryName = name;
        this.id = id;
    }

    // 普通方法
    public static String getCategoryName(int id) {
        for (MessageCategories c : MessageCategories.values()) {
            if (c.getId() == id) {
                return c.CategoryName;
            }
        }
        return null;
    }

    // 普通方法
    public static int getCategoryId(String categoryName) {
        for (MessageCategories c : MessageCategories.values()) {
            if (c.getCategoryName().equals(categoryName)) {
                return c.id;
            }
        }
        return 0;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
