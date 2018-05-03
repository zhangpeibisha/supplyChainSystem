package org.nix.dao.mapper;

import org.nix.dao.base.BaseMapper;
import org.nix.model.RoleModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Kiss
 * @date 2018/05/01 15:28
 */
@Repository
public interface RoleMapper extends BaseMapper<RoleModel> {

    void insertRoleList(List<RoleModel> roleModels);

    RoleModel selectRoleByName(String roleName);
}
