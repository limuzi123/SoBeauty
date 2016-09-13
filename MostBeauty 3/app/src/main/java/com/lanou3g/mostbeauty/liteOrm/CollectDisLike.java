package com.lanou3g.mostbeauty.liteOrm;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/9/12.
 */
@Table("collectDislike_db")
public class CollectDisLike {
    private String imgUrl;
    private int idUrl;
    @PrimaryKey(AssignType.AUTO_INCREMENT)//必须有
    private int id;

    public CollectDisLike(String imgUrl, int idUrl) {
        this.imgUrl = imgUrl;
        this.idUrl = idUrl;
    }

    public CollectDisLike(String imgUrl, int idUrl, int id) {
        this.imgUrl = imgUrl;
        this.idUrl = idUrl;
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(int idUrl) {
        this.idUrl = idUrl;
    }
}
