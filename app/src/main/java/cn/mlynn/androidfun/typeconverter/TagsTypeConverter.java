/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.typeconverter
 * @ClassName: TagsTypeConverter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/9 16:42
 */
package cn.mlynn.androidfun.typeconverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import cn.mlynn.androidfun.model.wan.Tag;

public class TagsTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public List<Tag> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Tag>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(List<Tag> someObjects) {
        return gson.toJson(someObjects);
    }

}
