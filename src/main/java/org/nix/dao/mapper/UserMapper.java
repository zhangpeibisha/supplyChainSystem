package org.nix.dao.mapper;

import org.nix.dao.base.BaseMapper;
import org.nix.model.UserModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kiss
 * @date 2018/04/22 14:57
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public interface UserMapper extends BaseMapper<UserModel> {
    UserModel login(UserModel model);
}
