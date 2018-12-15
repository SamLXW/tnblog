package tnblog.modules.authc.service;

import tnblog.modules.authc.entity.Permission;
import tnblog.modules.authc.entity.RolePermission;

import java.util.List;
import java.util.Set;

/**
 * @author - langhsu
 * @create - 2018/5/18
 */
public interface RolePermissionService {
    List<Permission> findPermissions(long roleId);
    void deleteByRoleId(long roleId);
    void add(Set<RolePermission> rolePermissions);

}
