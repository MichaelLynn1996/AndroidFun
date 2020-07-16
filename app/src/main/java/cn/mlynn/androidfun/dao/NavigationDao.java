/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.dao
 * @ClassName: NavigationDao
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 12:59
 */
package cn.mlynn.androidfun.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cn.mlynn.androidfun.model.wan.NavigationRoot;

@Dao
public interface NavigationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNavigation(List<NavigationRoot> list);

    @Query("SELECT * FROM NavigationRoot")
    LiveData<List<NavigationRoot>> loadAllNavigation();

    @Query("DELETE FROM NavigationRoot")
    void deleteAllNavigation();
}
