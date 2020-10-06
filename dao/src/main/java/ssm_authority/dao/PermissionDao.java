package ssm_authority.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Permission;

import java.util.List;

@Repository
public interface PermissionDao {

    /**
     * 根据roleId查找对应的Permission
     * @param id
     * @return java.util.List<ssm_authority.domain.Permission>
     * @date 2020/7/24 14:40
     */
    @Select("select permission.* from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findByRoleId(String id)throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Select("insert into permission values(#{id},#{permissionName},#{url})")
    void save(Permission permission);
}
