package com.lanou3g.mostbeauty.liteOrm;

import com.litesuits.orm.db.annotation.Table;

/**
 * Created by dllo on 16/9/12.
 */
@Table("collect_dislike_db")
public class CollectDisLike {
    private String imgUrl;
    private int idUrl;

    public CollectDisLike(String imgUrl, int idUrl) {
        this.imgUrl = imgUrl;
        this.idUrl = idUrl;
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
