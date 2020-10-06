package ssm_authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_authority.UUID.UUIDRandom;
import ssm_authority.dao.UsersDao;
import ssm_authority.domain.Role;
import ssm_authority.domain.UserInfo;
import ssm_authority.service.UsersService;

import java.util.ArrayList;
import java.util.List;
@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 查询所有用户
     * @param
     * @return java.util.List<ssm_authority.domain.UserInfo>
     * @date 2020/7/9 15:50
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return usersDao.findAll();
    }
    /**
     * 添加用户
     * @param userInfo
     * @return void
     * @date 2020/7/9 15:50
     */
    @Override
    public void save(UserInfo userInfo) throws Exception {
        String random = UUIDRandom.random();
        userInfo.setId(random);
        //对密码进行存储
       // passwordEncoder.
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        usersDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String userId) throws Exception {
        UserInfo userInfo =  usersDao.findById(userId);
        return userInfo;
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return   usersDao.findOtherRoles(userId);

    }

    @Override
    public void addRoles(String userId, String[] ids) throws Exception {
        for(String roleId:ids){
            usersDao.insertUserToRole(userId,roleId);
        }
    }

    /**
     * 登录验证
     * @param
     * @return org.springframework.security.core.userdetails.UserDetails
     * @date 2020/7/13 13:48
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = usersDao.findByUsername(username);
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);
        //"{noop}" +  没有进行加密时必须加
        User user = new User(userInfo.getUsername(),  userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true, true, authoritys);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }
}
