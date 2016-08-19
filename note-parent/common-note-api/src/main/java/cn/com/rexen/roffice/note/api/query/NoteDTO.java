package cn.com.rexen.roffice.note.api.query;

import cn.com.rexen.core.api.web.model.QueryDTO;

/**
 * Created by sunlf on 2015/11/5.
 */
public class NoteDTO extends QueryDTO {
    private String name; //项目名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}