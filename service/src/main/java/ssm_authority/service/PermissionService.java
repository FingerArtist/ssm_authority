package ssm_authority.service;

import org.springframework.stereotype.Service;
import ssm_authority.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll()throws Exception;

    void save(Permission permission)throws Exception;
}
