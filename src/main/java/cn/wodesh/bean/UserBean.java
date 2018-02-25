package cn.wodesh.bean;

import cn.wodesh.dao.annotation.FieldName;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * Created by TS on 2018/2/23.
 */
@Component
@Scope("prototype")
@TableName(name = "t_user")
public class UserBean {

    @ID
    private String uuid;

    @FieldName(name = "username")
    private String username;

    private String headurl;

    private String logintime;

    private String token;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
