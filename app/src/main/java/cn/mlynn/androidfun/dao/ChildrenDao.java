/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.dao
 * @ClassName: ChildrenDao
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 14:49
 */
package cn.mlynn.androidfun.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cn.mlynn.androidfun.model.wan.Children;

@Dao
public interface ChildrenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChildren(List<Children> list);

    @Query("SELECT * FROM Children")
    LiveData<List<Children>> loadAllChildren();

    @Query("DELETE FROM Children")
    void deleteAllChildren();
}
