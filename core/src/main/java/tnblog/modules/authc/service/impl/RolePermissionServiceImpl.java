package tnblog.modules.authc.service.impl;

import tnblog.modules.authc.dao.PermissionDao;
import tnblog.modules.authc.dao.RolePermissionDao;
import tnblog.modules.authc.entity.Permission;
import tnblog.modules.authc.entity.RolePermission;
import tnblog.modules.authc.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author - langhsu
 * @create - 2018/5/18
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Permission> findPermissions(long roleId) {
        List<RolePermission> rps = rolePermissionDao.findAllByRoleId(roleId);

        List<Permission> rets = null;
        if (rps != null && rps.size() > 0) {
            Set<Long> pids = new HashSet<>();
            rps.forEach(rp -> pids.add(rp.getPermissionId()));
            rets = permissionDao.findAllByIdIsIn(pids);
        }
        return rets;
    }

    @Override
    @Transactional
    public void deleteByRoleId(long roleId) {
        rolePermissionDao.deleteByRoleId(roleId);
    }

    @Override
    @Transactional
    public void add(Set<RolePermission> rolePermissions) {
        rolePermissionDao.save(rolePermissions);
    }
}
