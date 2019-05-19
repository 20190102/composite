package com.clx.composite.web;

import com.clx.composite.exception.AdminException;
import com.clx.composite.exception.DataInvalidException;
import com.clx.composite.model.DTO.AdminDTO;
import com.clx.composite.model.VO.UserListVO;
import com.clx.composite.service.AdminService;
import com.clx.composite.utils.CheckUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 获取用户列表
     *
     * @param query
     * @param page
     * @return
     */
    @GetMapping(value = {"/users", "/users/{query}"})
    public UserListVO listUsers(@PathVariable(required = false) String query,
                                @RequestParam(required = false, defaultValue = "1") int page) {

        return adminService.listUsers(query, page);
    }


    /**
     * 添加用户
     *
     * @param adminDTO
     * @return
     * @throws DataInvalidException
     */
    @PostMapping(value = "/users")
    public UserListVO insertUser(@RequestBody AdminDTO adminDTO) throws DataInvalidException, AdminException {

        CheckUtil.checkEmail(adminDTO.getEmail());
        CheckUtil.checkUsername(adminDTO.getUsername());
        CheckUtil.checkPassword(adminDTO.getPassword());
        CheckUtil.checkDate(adminDTO.getBirthday());
        CheckUtil.checkSex(adminDTO.getSex());
        CheckUtil.checkPermission(adminDTO.getPermission());

        return adminService.insertUser(adminDTO);
    }

    /**
     * 删除用户
     *
     * @param adminDTO
     * @return
     * @throws AdminException
     */
    @DeleteMapping(value = "/users")
    public UserListVO deleteUsers(@RequestBody AdminDTO adminDTO) throws AdminException {
        if (adminDTO.getIdList().size() == 0) throw new AdminException("无效数据");
        return adminService.deleteUsers(adminDTO);
    }

    /**
     * 修改用户数据
     *
     * @param adminDTO
     * @return
     */
    @PutMapping(value = "/users")
    public UserListVO updateUsers(@RequestBody AdminDTO adminDTO) throws AdminException, DataInvalidException {

        CheckUtil.checkEmail(adminDTO.getEmail());
        CheckUtil.checkUsername(adminDTO.getUsername());
        CheckUtil.checkDate(adminDTO.getBirthday());
        CheckUtil.checkSex(adminDTO.getSex());
        CheckUtil.checkPermission(adminDTO.getPermission());

        return adminService.updateUser(adminDTO);
    }

    /**
     * 上传excel文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public UserListVO upload(MultipartFile file) {
        return adminService.uploadExcel(file);
    }

    /**
     * 获取redis中的excel文件数据
     *
     * @param page
     * @return
     */
    @GetMapping(value = "/excel")
    public UserListVO listExcel(int page) {
        return adminService.getLrange(page);
    }

    @GetMapping(value = "/file")
    public void download(HttpServletResponse res) throws IOException {
        File file = new File("E:\\模板.xlsx");

        OutputStream out = res.getOutputStream();
        out.write(FileUtils.readFileToByteArray(file));
        out.close();
    }

}
