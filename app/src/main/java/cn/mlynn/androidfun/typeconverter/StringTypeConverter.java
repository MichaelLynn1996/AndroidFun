/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.typeconverter
 * @ClassName: StringTypeConverter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/5 20:30
 */
package cn.mlynn.androidfun.typeconverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StringTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public List<String> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToString(List<String> someObjects) {
        return gson.toJson(someObjects);
    }
//————————————————
//    版权声明：本文为CSDN博主「慢行的骑兵」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/ittalmud/article/details/96440662
}
