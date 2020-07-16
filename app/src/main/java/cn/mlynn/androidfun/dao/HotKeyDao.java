/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.dao
 * @ClassName: HotKeyDao
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:39
 */
package cn.mlynn.androidfun.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cn.mlynn.androidfun.model.wan.HotKey;

@Dao
public interface HotKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotKey(List<HotKey> list);

    @Query("SELECT * FROM HotKey")
    LiveData<List<HotKey>> loadAllHotKey();

    @Query("DELETE FROM HotKey")
    void deleteAllHotKey();
}
