package com.clx.composite.web;

import com.clx.composite.model.LoginVO;
import com.clx.composite.model.RequestDTO;
import com.clx.composite.model.UserDO;
import com.clx.composite.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@CrossOrigin
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginVO loginVO;

    /**
     * 获取用户数据
     * @param queryInfo
     * @param page
     * @return
     */
    @RequestMapping(value = {"/users","/users/{queryInfo}"}, method = RequestMethod.GET)
    public LoginVO listQueryUsers(@PathVariable(required = false)String queryInfo,
                                  @RequestParam(required = false,defaultValue = "1") int page) {
        //这个code的值由拦截器校验token后设置
        if(loginVO.getCode()==200){
            //前端不传queryInfo默认为获取所有数据，@PathVariable没有默认值功能，所以手动设没有传值时值为""
            if(queryInfo==null)queryInfo="";
            PageInfo<UserDO> pageDTO = userService.listUsers(queryInfo,page);
            loginVO.setInfo(pageDTO);
        }else {
            loginVO.setInfo(null);
        }
        return loginVO;
    }

    /**
     * 添加用户
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public boolean insertUser(@RequestBody RequestDTO requestDTO) {
        return userService.insertUser(requestDTO);
    }

    /**
     * 删除数据
     * @param requestDTO idList
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public boolean deleteUsers(@RequestBody RequestDTO requestDTO) {
        return userService.deleteUsers(requestDTO);
    }

    /**
     * 更新数据
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public boolean updateUsers(@RequestBody RequestDTO requestDTO) {
        return userService.updateUser(requestDTO);
    }

}
