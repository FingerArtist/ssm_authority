package ssm_authority.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ssm_authority.domain.Role;
import ssm_authority.domain.UserInfo;

import java.util.List;

public interface UsersService extends UserDetailsService {


    List<UserInfo> findAll()throws Exception;

    void save(UserInfo userInfo)throws Exception;

    UserInfo findById(String userid)throws Exception;

    List<Role> findOtherRoles(String userid)throws Exception;

    void addRoles(String userId, String[] ids)throws Exception;
}
