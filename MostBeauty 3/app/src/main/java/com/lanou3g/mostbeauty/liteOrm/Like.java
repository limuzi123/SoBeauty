package com.lanou3g.mostbeauty.liteOrm;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/9/8.
 */
@Table("like_db")
public class Like {
    private String imgUrl,name,content;

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    public Like(String imgUrl, String name, String content) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.content = content;
    }

    public Like(String imgUrl, String name, String content, int id) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.content = content;
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
