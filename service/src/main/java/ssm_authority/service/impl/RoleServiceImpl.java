package ssm_authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_authority.UUID.UUIDRandom;
import ssm_authority.dao.RoleDao;
import ssm_authority.domain.Role;
import ssm_authority.service.RoleService;

import java.util.List;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        String random = UUIDRandom.random();
        role.setId(random);
        roleDao.save(role);
    }

    @Override
    public Role findById(String id)throws Exception {
        return roleDao.findById(id);
    }
}
