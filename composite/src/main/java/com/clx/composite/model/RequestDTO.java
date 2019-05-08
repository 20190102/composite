package com.clx.composite.model;


import java.util.List;

/**
 * 从页面获取的数据
 */
public class RequestDTO extends UserDO {
    private boolean remember;   //是否记住登录状态
    private String verificationCode;//验证码
    private String queryInfo;//搜索信息
    private int page=1;//页数
    private List<Integer> idList;//删除的id列表

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getQueryInfo() {
        return queryInfo;
    }

    public void setQueryInfo(String queryInfo) {
        this.queryInfo = queryInfo;
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


}
