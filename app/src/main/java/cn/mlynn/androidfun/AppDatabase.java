/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun
 * @ClassName: AppDatabase
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 13:07
 */
package cn.mlynn.androidfun;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cn.mlynn.androidfun.dao.ChildrenDao;
import cn.mlynn.androidfun.dao.HotKeyDao;
import cn.mlynn.androidfun.dao.NavigationDao;
import cn.mlynn.androidfun.dao.SearchRecordDao;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.Children;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.model.wan.NavigationRoot;

@Database(entities = {NavigationRoot.class
        , Children.class
        , SearchRecord.class, HotKey.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * 单例模式
     * volatile 确保线程安全
     * 线程安全意味着改对象会被许多线程使用
     * 可以被看作是一种 “程度较轻的 synchronized”
     */
    private static volatile AppDatabase INSTANCE;

    /**
     * 该方法由于获得 DataBase 对象
     * abstract
     *
     * @return
     */
    public abstract NavigationDao getNavigationDao();

    public abstract ChildrenDao getChildrenDao();

    public abstract SearchRecordDao getSearchRecordDao();

    public abstract HotKeyDao getHotKeyDao();

    public static AppDatabase getInstance(Context context) {
        // 若为空则进行实例化
        // 否则直接返回
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
