package com.clx.composite.model.DTO;

import com.clx.composite.utils.ConvertUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AdminDTO {
    private int id;
    private String username;
    private String email;
    private String password;
    private String birthday;
    private String sex;
    private String signUpDay= ConvertUtil.dataFormat(new Date());
    private String permission;
    private String query = "";
    private int page = 1;
    private List<Integer> idList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignUpDay() {
        return signUpDay;
    }

    public void setSignUpDay(String signUpDay) {
        this.signUpDay = signUpDay;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", signUpDay='" + signUpDay + '\'' +
                ", permission='" + permission + '\'' +
                ", query='" + query + '\'' +
                ", page=" + page +
                ", idList=" + idList +
                '}';
    }
}
