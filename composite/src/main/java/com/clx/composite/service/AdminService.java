package com.clx.composite.service;

import com.clx.composite.exception.AdminException;
import com.clx.composite.mapper.AdminMapper;
import com.clx.composite.model.DO.ExcelDO;
import com.clx.composite.model.DO.UserDO;
import com.clx.composite.model.DTO.AdminDTO;
import com.clx.composite.model.ExcelInfo;
import com.clx.composite.model.VO.UserListVO;
import com.clx.composite.utils.ExcelUtil;
import com.clx.composite.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserListVO userListVO;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户列表,使用pagehelper插件分页
     *
     * @param query 搜索信息
     * @param page  第几页
     * @return
     */
    public UserListVO listUsers(String query, int page) {

        PageHelper.startPage(page, 10);
        List<UserDO> list = adminMapper.listUsers(query);
        PageInfo<UserDO> info = new PageInfo<>(list);

        userListVO.setCode(HttpStatus.OK.value());
        userListVO.setInfo(info);
        userListVO.setMsg("用户信息获取成功");

        return userListVO;
    }

    /**
     * 添加用户
     *
     * @param adminDTO
     * @return
     */
    public UserListVO insertUser(AdminDTO adminDTO) throws AdminException {
        if (adminMapper.hasRepeatEmail(adminDTO) == 0) {
            if (adminMapper.insertUser(adminDTO) > 0) {

                userListVO.setCode(HttpStatus.OK.value());
                userListVO.setMsg("添加用户成功");
                userListVO.setInfo(null);

                return userListVO;
            }
        }
        throw new AdminException("该邮箱已存在");
    }

    /**
     * 修改用户数据
     *
     * @param adminDTO
     * @return
     */
    public UserListVO updateUser(AdminDTO adminDTO) throws AdminException {
        if (adminMapper.hasRepeatEmail(adminDTO) == 0) {
            if (adminMapper.updateUser(adminDTO) == 1) {

                userListVO.setMsg("用户修改成功");
                userListVO.setCode(HttpStatus.OK.value());
                userListVO.setInfo(null);

                return userListVO;
            }
        }
        throw new AdminException("邮箱已存在");
    }

    /**
     * 删除用户
     *
     * @param adminDTO
     * @return
     */
    public UserListVO deleteUsers(AdminDTO adminDTO) throws AdminException {
        int delNum = adminMapper.deleteUsers(adminDTO.getIdList());
        int idNum = adminDTO.getIdList().size();
        //如果被删除的行数等于传的id个数，视为删除成功
        if (delNum == idNum) {
            userListVO.setCode(HttpStatus.OK.value());
            userListVO.setInfo(null);
            userListVO.setMsg("删除用户成功");

            return userListVO;
        }
        throw new AdminException("发生错误，实际删除用户数量与预期不一致");

    }


    /**
     * 解析，存储excel文件
     *
     * @param file
     * @return
     */
    public UserListVO uploadExcel(MultipartFile file) {

        List<ExcelDO> list = ExcelUtil.excelResolver(file);


        redisUtil.rpush("excel", list);

        return getLrange(1);
    }

    /**
     * 从redis中获取excel数据
     *
     * @param pageNum 要获取的页数
     * @return
     */
    public UserListVO getLrange(int pageNum) {
        int start = (pageNum - 1) * 10;
        List<ExcelDO> list = redisUtil.lrange("excel", start, start + 9);
        long size= redisUtil.llen("excel");
        int pages;
        if(size%10==0){
            pages= (int) (size/10);
        }else {
            pages= (int) (size/10)+1;
        }
        ExcelInfo<ExcelDO> info=new ExcelInfo();
        info.setList(list);
        info.setPages(pages);
        info.setPageNum(pageNum);

        userListVO.setMsg("excel数据获取成功");
        userListVO.setInfo(info);
        userListVO.setCode(HttpStatus.OK.value());

        return userListVO;
    }


}
