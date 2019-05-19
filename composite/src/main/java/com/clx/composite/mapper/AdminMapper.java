package com.clx.composite.mapper;


import com.clx.composite.model.DO.UserDO;
import com.clx.composite.model.DTO.AdminDTO;

import java.util.List;

public interface AdminMapper {
    int insertUser(AdminDTO adminDTO);

    int deleteUsers(List<Integer> idList);

    List<UserDO> listUsers(String query);

    int updateUser(AdminDTO adminDTO);

    int hasRepeatEmail(AdminDTO adminDTO);
}
