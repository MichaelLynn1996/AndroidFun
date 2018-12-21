package xyz.sealynn.androidfun.model;

import java.util.List;

/**
 * Created by SeaLynn0 on 2018/12/10 20:40
 * <p>
 * Email：sealynndev@gmail.com
 */
public class LoginData {
    private List<String> chaptertops;
    private List<Integer> collectids;
    private String email;
    private String icon;
    private int id;
    private String password;
    private String token;
    private int type;
    private String username;


    public void setChaptertops(List<String> chaptertops) {
        this.chaptertops = chaptertops;
    }

    public List<String> getChaptertops() {
        return chaptertops;
    }


    public void setCollectids(List<Integer> collectids) {
        this.collectids = collectids;
    }

    public List<Integer> getCollectids() {
        return collectids;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
