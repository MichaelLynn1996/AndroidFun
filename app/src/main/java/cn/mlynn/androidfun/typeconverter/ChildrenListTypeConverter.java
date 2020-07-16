/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.typeconverter
 * @ClassName: ChildrenListTypeConverter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 15:11
 */
package cn.mlynn.androidfun.typeconverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import cn.mlynn.androidfun.model.wan.Children;

public class ChildrenListTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public List<Children> stringToChildrenList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Children>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToChildrenList(List<Children> someObjects) {
        return gson.toJson(someObjects);
    }
}
