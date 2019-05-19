package com.clx.composite.model.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户列表页面返回的信息
 *
 * @param <T>
 */
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserListVO<T> extends BaseVO {

    private T info;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

}
