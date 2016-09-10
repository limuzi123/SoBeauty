package com.lanou3g.mostbeauty.liteOrm;

import com.lanou3g.mostbeauty.base.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class OrmTool {
    private static OrmTool ourInstance;
    private static LiteOrm liteOrm;


    public static OrmTool getInstance() {
        if (ourInstance == null) {
            synchronized ((OrmTool.class)) {
                if (ourInstance == null) {
                    ourInstance = new OrmTool();

                }
            }
        }
        return ourInstance;
    }

    private OrmTool() {
        liteOrm = LiteOrm.newCascadeInstance(MyApp.getContext(), "net.db");
    }



    public void insertCollect(Collect collect) {
        liteOrm.insert(collect);


    }

    public void inertLike(Like like) {
        liteOrm.insert(like);

    }

    public void insertCare(Care care) {
        liteOrm.insert(care);
    }

    public void saveCollect(Collect collect) {
        liteOrm.save(collect);
    }

    public void saveLike(Like like) {
        liteOrm.save(like);

    }

    public void saveCare(Care care) {
        liteOrm.save(care);
    }

    public List<Collect> getAllCollect() {
        return liteOrm.query(Collect.class);

    }

    public List<Like> getAllLike() {
        return liteOrm.query(Like.class);
    }

    public List<Care> getAllCare() {
        return liteOrm.query(Care.class);
    }

    public void deleteLike() {
        liteOrm.deleteAll(Like.class);
    }
    public void deleteCare(){
        liteOrm.deleteAll(Care.class);

    }
    public void deletaCareByName(Care care){

        liteOrm.delete(WhereBuilder.create(Care.class).where("name" + "=?", new String[]{care.getName()}));
    }
public void deletaLikeByName(Like like){

   liteOrm.delete(WhereBuilder.create(Like.class).where("name" + "=?", new String[]{like.getName()}));
}
    public List<Like> getLikeName(Like like) {
        QueryBuilder<Like> likeQueryBuilder = new QueryBuilder<Like>(Like.class)
                .where("name" + "=?", new String[]{like.getName()});
        return liteOrm.query(likeQueryBuilder);

    }
    public List<Like> getLikeImg(Like like) {
        QueryBuilder<Like> likeQueryBuilder = new QueryBuilder<Like>(Like.class)
                .where("imgUrl" + "=?", new String[]{like.getImgUrl()});
        return liteOrm.query(likeQueryBuilder);

    }
}