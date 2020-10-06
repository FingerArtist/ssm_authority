package ssm_authority.service;

import ssm_authority.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll()throws Exception;

    void save(Role role)throws Exception;

    Role findById(String id) throws Exception;
}
