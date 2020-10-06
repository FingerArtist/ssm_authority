package ssm_authority.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Member;
import ssm_authority.domain.Orders;
import ssm_authority.domain.Product;

import java.util.List;

@Repository
public interface OrdersDao {
    /**
     * 查找所有订单
     * @param
     * @return java.util.List<ssm_authority.domain.Orders>
     * @date 2020/7/7 15:15
     */
    @Select("select * from orders")
    @Results(id = "ordersProduct",
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "orderNum",property = "orderNum"),
                    @Result(column = "orderTime",property = "orderTime"),
                    @Result(column = "peopleCount",property = "peopleCount"),
                    @Result(column = "orderDesc",property = "orderDesc"),
                    @Result(column = "payType",property = "payType"),
                    @Result(column = "orderStatus",property = "orderStatus"),
                    @Result(column = "productId",property = "productId"),
                    @Result(column = "memberId",property = "memberId"),
                    @Result(column = "productId",property = "product",javaType = Product.class,one = @One(
                            select = "ssm_authority.dao.ProductDao.findById"
                    ))
            }
    )
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results(id = "ordersProductMemberTraveller",
            value = {
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "orderNum",property = "orderNum"),
                    @Result(column = "orderTime",property = "orderTime"),
                    @Result(column = "peopleCount",property = "peopleCount"),
                    @Result(column = "orderDesc",property = "orderDesc"),
                    @Result(column = "payType",property = "payType"),
                    @Result(column = "orderStatus",property = "orderStatus"),
                    @Result(column = "productId",property = "productId"),
                    @Result(column = "memberId",property = "memberId"),
                    @Result(column = "productId",property = "product",javaType = Product.class,one = @One(
                            select = "ssm_authority.dao.ProductDao.findById"
                    )),
                    @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(
                          select = "ssm_authority.dao.MemberDao.findById"
                    )),
                    @Result(column = "id",property = "travellers",javaType = List.class, many = @Many(
                            select = "ssm_authority.dao.TravellerDao.findByOrdersId"
                    ))

            })
    Orders findDetailById(String id)throws Exception;

}
