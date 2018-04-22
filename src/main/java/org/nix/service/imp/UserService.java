package org.nix.service.imp;

import org.nix.dao.mapper.UserMapper;
import org.nix.model.UserModel;
import org.nix.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by 11723 on 2017/5/4.
 */
@Service
public class UserService extends BaseService<UserModel> {
    @Autowired
    private UserMapper userMapper;
    /**
     * 验证用户登录信息
     * */
    public UserModel login(UserModel model){
        return userMapper.login(model);
    }
}
