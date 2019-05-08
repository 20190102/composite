package com.clx.composite.service;


import com.clx.composite.mapper.UserMapper;
import com.clx.composite.model.RequestDTO;
import com.clx.composite.model.UserDO;
import com.clx.composite.utils.CaptchaUtil;
import com.clx.composite.utils.CheckUtil;
import com.clx.composite.utils.ConvertUtil;
import com.clx.composite.utils.JwtUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


    public String login(RequestDTO requestDTO) {
        //创建token的有效时长
        int timeout=60*30;
        //当remember为true时，token有效期为7天
        if(requestDTO.isRemember()){
            timeout=60*60*24*7;
        }
        if (userMapper.login(requestDTO)) {
            //如果登录成功，返回一个token字符串
            return JwtUtil.createToken(requestDTO.getEmail(), ConvertUtil.secondToDate(timeout));
        }
        return "";
    }

    public PageInfo<UserDO> listUsers(String queryInfo,int page) {
            PageHelper.startPage(page, 10);
            List<UserDO> list = userMapper.listUsers(queryInfo);
        return new PageInfo<UserDO>(list);
    }

    /**
     * 添加用户
     * @param requestDTO
     * @return
     */
    public boolean insertUser(RequestDTO requestDTO) {
        if(!userMapper.hasRepeatEmail(requestDTO)){
            //设置添加时间
            requestDTO.setRegisterDay(sdf.format(new Date()));
            return userMapper.insertUser(requestDTO) > 0;
        }
        return false;
    }

    /**
     * 用户注册
     * @param requestDTO
     * @return
     */
    public boolean register(RequestDTO requestDTO){
        if(!userMapper.hasRepeatEmail(requestDTO)){
            //设置注册时间
            requestDTO.setRegisterDay(sdf.format(new Date()));
            return  userMapper.insertUser(requestDTO) > 0;
        }
        return false;

    }

    /**
     * 删除用户
     * @param requestDTO
     * @return
     */
    public boolean deleteUsers(RequestDTO requestDTO) {
       int delNum= userMapper.deleteUsers(requestDTO.getIdList());
       int idNum=requestDTO.getIdList().size();
       //如果被删除的行数等于传的id个数，视为删除成功
        return  delNum==idNum ;
    }

    /**
     * 更新用户
     * @param requestDTO
     * @return
     */
    public boolean updateUser(RequestDTO requestDTO) {
        if(!userMapper.hasRepeatEmail(requestDTO)){
           return userMapper.updateUser(requestDTO) == 1;
        }
        return false;
    }

    /**
     * 发送验证码
     * @param requestDTO
     * @return
     */
    public boolean sendCode(RequestDTO requestDTO) {
        if(!userMapper.hasRepeatEmail(requestDTO)){
            //获得一个验证码
            String code = CaptchaUtil.createAndSaveCode(requestDTO.getEmail());
            String subject = "测试验证码";
            String Text = "验证码" + code;
            //将验证码发送到传入的邮箱
            return CaptchaUtil.sendMail(requestDTO.getEmail(), subject, Text);
        }
        return false;

    }
}
