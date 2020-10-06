package ssm_authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_authority.UUID.UUIDRandom;
import ssm_authority.dao.PermissionDao;
import ssm_authority.domain.Permission;
import ssm_authority.service.PermissionService;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {

        String random = UUIDRandom.random();
        permission.setId(random);
        permissionDao.save(permission);
    }
}
