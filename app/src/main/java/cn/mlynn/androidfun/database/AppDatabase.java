/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun
 * @ClassName: AppDatabase
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 13:07
 */
package cn.mlynn.androidfun.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cn.mlynn.androidfun.database.dao.SearchRecordDao;
import cn.mlynn.androidfun.model.local.SearchRecord;

@Database(entities = {SearchRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

//    /**
//     * 单例模式
//     * volatile 确保线程安全
//     * 线程安全意味着改对象会被许多线程使用
//     * 可以被看作是一种 “程度较轻的 synchronized”
//     */
//    private static volatile AppDatabase INSTANCE;
    public abstract SearchRecordDao getSearchRecordDao();

//    public static AppDatabase getInstance(Context context) {
//        // 若为空则进行实例化
//        // 否则直接返回
//        if (INSTANCE == null) {
//            synchronized (AppDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context,
//                            AppDatabase.class, "app_database")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
////        //  使用HILT后的写法
////        return Room.databaseBuilder(context,
////                            AppDatabase.class, "app_database")
////                            .build();
//    }


}
