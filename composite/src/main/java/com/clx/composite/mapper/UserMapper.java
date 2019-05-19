package com.clx.composite.mapper;

import com.clx.composite.model.DO.UserDO;
import com.clx.composite.model.DTO.UserDTO;

import java.util.List;

public interface UserMapper {

    public boolean login(UserDTO userDTO);

    public List<UserDO> listUsers(String query);

    public int signUp(UserDTO userDTO);


    public int hasRepeatEmail(UserDTO userDTO);
}
