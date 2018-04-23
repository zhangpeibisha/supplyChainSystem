package org.nix.dao.mapper;

import org.nix.dao.base.BaseMapper;
import org.nix.model.UserModel;
import org.springframework.stereotype.Repository;

/**
 * @author Kiss
 * @date 2018/04/22 14:57
 */
@Repository
public interface UserMapper extends BaseMapper<UserModel> {
    UserModel login(UserModel model);
}
