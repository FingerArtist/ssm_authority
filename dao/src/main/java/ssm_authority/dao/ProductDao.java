package ssm_authority.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_authority.domain.Product;

import java.util.List;
@Repository
public interface ProductDao {




    /**
     * 查找所有商品
     * @param
     * @return java.util.List<ssm_authority.domain.Product>
     * @date 2020/7/7 14:54
     */
    @Select("select * from product")
     List<Product> findAll() throws Exception;

    /**
     * 根据id查询信息
     * @param id
     * @return ssm_authority.domain.Product
     * @date 2020/7/7 14:55
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;

    /**
     * 添加商品
     * @param product
     * @return void
     * @date 2020/7/7 14:54
     */
    @Select("insert into product values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
