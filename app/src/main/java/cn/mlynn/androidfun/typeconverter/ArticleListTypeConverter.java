/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.typeconverter
 * @ClassName: ArticleListTypeConverter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 13:25
 */
package cn.mlynn.androidfun.typeconverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import cn.mlynn.androidfun.model.wan.Article;

public class ArticleListTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public List<Article> stringToArticleList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Article>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String someObjectListToArticleList(List<Article> someObjects) {
        return gson.toJson(someObjects);
    }
}
