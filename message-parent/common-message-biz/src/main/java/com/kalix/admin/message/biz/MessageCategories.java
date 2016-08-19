package com.kalix.admin.message.biz;

/**
 * Created by zangyanming on 2016/3/3.
 */
public enum MessageCategories {
    MESSAGE("系统消息", 1), SUGGESTION("建议", 2), WARNING("警告", 3), ASSIGNMENT("分配", 4);
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
