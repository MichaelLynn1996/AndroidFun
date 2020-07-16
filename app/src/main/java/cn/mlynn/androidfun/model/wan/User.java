package cn.mlynn.androidfun.model.wan;

import java.util.List;

/**
 * Created by SeaLynn0 on 2018/12/10 20:40
 * <p>
 * Email：sealynndev@gmail.com
 */
public class User {

    private boolean admin;
    private List<String> chaptertops;
    private int coincount;
    private List<Integer> collectids;
    private String email;
    private String icon;
    private int id;
    private String nickname;
    private String password;
    private String publicname;
    private String token;
    private int type;
    private String username;


    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public boolean getAdmin() {
        return admin;
    }


    public void setChaptertops(List<String> chaptertops) {
        this.chaptertops = chaptertops;
    }
    public List<String> getChaptertops() {
        return chaptertops;
    }


    public void setCoincount(int coincount) {
        this.coincount = coincount;
    }
    public int getCoincount() {
        return coincount;
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


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }


    public void setPublicname(String publicname) {
        this.publicname = publicname;
    }
    public String getPublicname() {
        return publicname;
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


