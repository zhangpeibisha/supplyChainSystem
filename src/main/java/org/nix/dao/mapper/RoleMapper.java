package org.nix.dao.mapper;

import org.nix.dao.base.BaseMapper;
import org.nix.model.RoleModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kiss
 * @date 2018/05/01 15:28
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public interface RoleMapper extends BaseMapper<RoleModel> {

    void insertRoleList(List<RoleModel> roleModels);

    RoleModel selectRoleByName(String roleName);
}
