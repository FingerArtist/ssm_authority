package ssm_authority.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Role;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role")
    List<Role> findAll()throws Exception;

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    List<Role> findByUserId(String id)throws Exception;

    /**
     * 角色详情查看
     * @param id
     * @return ssm_authority.domain.Role
     * @date 2020/7/24 14:44
     */
    @Select("select * from role where id = #{id}")
    @Results(
           value = {
                   @Result(id = true,column = "id",property = "id"),
                   @Result(column = "roleName",property = "roleName"),
                   @Result(column = "roleDesc",property = "roleDesc"),
                   @Result(column = "id",property = "permissions",many = @Many(select = "ssm_authority.dao.PermissionDao.findByRoleId"),javaType = List.class
                   )
           }
    )
    Role findById(@Param("id") String id);
}
