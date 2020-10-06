package ssm_authority.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.SysLog;

import java.util.List;

@Repository
public interface SysLogDao {

    @Select("insert into syslog values(null,#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);


    @Select("select * from syslog")
    List<SysLog> findAll();
}
