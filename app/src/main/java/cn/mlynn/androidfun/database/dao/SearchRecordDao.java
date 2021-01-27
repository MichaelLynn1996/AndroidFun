/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.database.dao
 * @ClassName: SearchRecordDao
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:06
 */
package cn.mlynn.androidfun.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cn.mlynn.androidfun.model.local.SearchRecord;

@Dao
public interface SearchRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchRecord(SearchRecord record);

    @Query("SELECT * FROM SearchRecord ORDER BY `key` DESC")
    LiveData<List<SearchRecord>> loadAllSearchRecord();

    @Query("DELETE FROM SearchRecord WHERE text == :text")
    void deleteSearchRecordByContent(String text);

    @Query("DELETE FROM SearchRecord")
    void deleteAllSearchRecord();
}
