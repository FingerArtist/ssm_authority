package ssm_authority.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Role;
import ssm_authority.domain.UserInfo;

import java.util.List;

@Repository
public interface UsersDao {

    @Select("select * from users")
    List<UserInfo> findAll()throws Exception;

    @Select("select * from users where username=#{username}")
    @Results(
            value = {@Result(id = true, property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "phoneNum", column = "phoneNum"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "roles",column = "id",many = @Many(select = "ssm_authority.dao.RoleDao.findByUserId"), javaType = List.class)
            }
    )
    UserInfo findByUsername(String username);

    /**
     * 增加用户
     * @param userInfo
     * @return void
     * @date 2020/7/13 15:12
     */
    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{userId}")
    @Results(
          value = {@Result(id = true, property = "id", column = "id"),
                  @Result(property = "username", column = "username"),
                  @Result(property = "email", column = "email"),
                  @Result(property = "password", column = "password"),
                  @Result(property = "phoneNum", column = "phoneNum"),
                  @Result(property = "status", column = "status"),
                  @Result(property = "roles",column = "id",many = @Many(select = "ssm_authority.dao.RoleDao.findByUserId"), javaType = List.class)
          }
    )
    UserInfo findById(String userId);

    /**
     * 查找还可以添加的角色
     * @param userId
     * @return java.util.List<ssm_authority.domain.Role>
     * @date 2020/7/13 15:33
     */
    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId);
    /**
     * 用户和角色关系添加
     * @param userId
     * @param id
     * @return void
     * @date 2020/7/13 15:51
     */
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void insertUserToRole(@Param("userId") String userId,@Param("roleId") String roleId);
}
