/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.model
 * @ClassName: SearchRecord
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:03
 */
package cn.mlynn.androidfun.model.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import cn.mlynn.androidfun.model.BaseEntity;

//@Entity(primaryKeys = {"key"})
@Entity
public class SearchRecord {

    @PrimaryKey(autoGenerate = true)
    private int key;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
