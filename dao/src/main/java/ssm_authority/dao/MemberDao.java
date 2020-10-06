package ssm_authority.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Member;

@Repository
public interface MemberDao {

    @Select("select * from member")
    Member findById(String id)throws Exception;
}
