package com.lanou3g.mostbeauty.liteOrm;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/9/8.
 */
//实体类
@Table("care_db")
public class Care {
    private String imgBigUrl,imgNameUrl,name,title;
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    public Care(String imgBigUrl, String imgNameUrl, String name, String title, int id) {
        this.imgBigUrl = imgBigUrl;
        this.imgNameUrl = imgNameUrl;
        this.name = name;
        this.title = title;
        this.id = id;
    }

    public Care(String imgBigUrl, String imgNameUrl, String name, String title) {
        this.imgBigUrl = imgBigUrl;
        this.imgNameUrl = imgNameUrl;
        this.name = name;
        this.title = title;
    }

    public String getImgBigUrl() {
        return imgBigUrl;
    }

    public void setImgBigUrl(String imgBigUrl) {
        this.imgBigUrl = imgBigUrl;
    }

    public String getImgNameUrl() {
        return imgNameUrl;
    }

    public void setImgNameUrl(String imgNameUrl) {
        this.imgNameUrl = imgNameUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
