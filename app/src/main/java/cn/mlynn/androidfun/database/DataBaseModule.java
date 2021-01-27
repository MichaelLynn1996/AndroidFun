/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun
 * @ClassName: DataBaseModule
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/21 23:02
 */
package cn.mlynn.androidfun.database;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import cn.mlynn.androidfun.database.dao.SearchRecordDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(Application application){
        return Room.databaseBuilder(application,
                AppDatabase.class, "app_database")
                .build();
    }

    @Provides
    @Singleton
    public SearchRecordDao provideSearchRecordDao(AppDatabase database) {
        return database.getSearchRecordDao();
    }
}
