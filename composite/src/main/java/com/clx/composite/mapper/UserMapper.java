package com.clx.composite.mapper;

import com.clx.composite.model.RequestDTO;
import com.clx.composite.model.UserDO;

import java.util.List;

public interface UserMapper {

    public boolean login(RequestDTO requestDTO);

    public List<UserDO> listUsers(String queryInfo);

    public int insertUser(RequestDTO requestDTO);

    public int deleteUsers(List<Integer> idList);

    public int updateUser(RequestDTO requestDTO);

    public boolean hasRepeatEmail(RequestDTO requestDTO);
}
