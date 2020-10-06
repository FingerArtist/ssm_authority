package ssm_authority.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Traveller;

import java.util.List;

@Repository
public interface TravellerDao {

    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})")
    List<Traveller> findByOrdersId(String id)throws Exception;
}
