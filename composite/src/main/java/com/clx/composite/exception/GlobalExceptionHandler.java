package com.clx.composite.exception;

import com.clx.composite.model.VO.LoginVO;
import com.clx.composite.model.VO.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
/**
 * 异常处理
 */
public class GlobalExceptionHandler {


    @Autowired
    private LoginVO loginVO;
    @Autowired
    private UserListVO userListVO;

    @ExceptionHandler
    public LoginVO exception(Exception e) {
        System.out.println("写入日志文件");
        loginVO.setMsg("服务器错误，请稍后再试");
        loginVO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        loginVO.setToken("");
        return loginVO;
    }


    @ExceptionHandler
    public LoginVO excelException(ExcelException e) {
        loginVO.setMsg(e.getMessage());
        loginVO.setCode(HttpStatus.BAD_REQUEST.value());
        return loginVO;
    }

    /**
     * 数据格式异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public LoginVO DataInvalidException(DataInvalidException e) {

        loginVO.setCode(HttpStatus.BAD_REQUEST.value());
        loginVO.setMsg(e.getMessage());
        loginVO.setToken("");
        return loginVO;
    }

    @ExceptionHandler
    public UserListVO adminException(AdminException e) {
        userListVO.setInfo(null);
        userListVO.setCode(HttpStatus.BAD_REQUEST.value());
        userListVO.setMsg(e.getMessage());
        userListVO.setToken(null);
        return userListVO;
    }

    @ExceptionHandler
    public LoginVO userException(UserException e) {
        loginVO.setCode(HttpStatus.BAD_REQUEST.value());
        loginVO.setMsg(e.getMessage());

        return loginVO;
    }

}
