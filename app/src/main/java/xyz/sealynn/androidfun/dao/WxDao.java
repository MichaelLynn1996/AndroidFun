package xyz.sealynn.androidfun.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import xyz.sealynn.androidfun.model.wxarticle.WxActicle;

/**
 * Created by MichaelLynn on 2020/2/4 16:54
 * <p>
 * Email: sealynndev@gamil.com
 */
@Dao
public interface WxDao {

    @Query("SELECT * FROM WxActicle ORDER BY curPage COLLATE NOCASE ASC")
    DataSource.Factory<Integer, WxActicle> queryWxActicleByPage();
}
