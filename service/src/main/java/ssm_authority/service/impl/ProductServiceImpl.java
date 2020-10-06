package ssm_authority.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_authority.ProductUtils;
import ssm_authority.UUID.UUIDRandom;
import ssm_authority.dao.ProductDao;
import ssm_authority.domain.Product;
import ssm_authority.service.ProductService;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
   private ProductDao productDao;
    /**
     * 查找所有产品
     * @param
     * @return java.util.List<ssm_authority.domain.Product>
     * @date 2020/7/2 13:55
     */
    @Override
    public List<Product> findAll(Integer pageNum,Integer pageSize) throws Exception {
        //在执行sql语句之前调用分页
        PageHelper.startPage(pageNum,pageSize);
        List<Product> all = productDao.findAll();
        return all;
    }
    /**
     * 新增产品
     * @param product
     * @return void
     * @date 2020/7/3 12:54
     */
    @Override
    public void save(Product product) throws Exception {
        //生成ID
        product.setId(UUIDRandom.random());
        //生成产品编号 日期加UUID
        product.setProductNum(product.getDepartureTimeStr()+product.getId());
        productDao.save(product);
    }

    @Override
    public void saveRandom() throws Exception {

        for (int l = 0;l<100;l++) {

            Product product = new Product();
            //生成ID
            product.setId(UUIDRandom.random());


            String[] citys = {"上海", "北京", "Canada", "America", "Britain", "England", "Tokyo", "Japan"};
            Random random = new Random();
            int from = random.nextInt(citys.length);
            //随机设置城市
            product.setCityName(citys[from]);
            int to;
            while ((to = random.nextInt(citys.length)) == from) {

            }
            //设置产品名称
            product.setProductName(citys[from] + "---" + citys[to]);


            int time = random.nextInt((int)System.currentTimeMillis());
           long time1 =  System.currentTimeMillis() - time;
            product.setDepartureTime(new Date(time1));

            //生成产品编号 日期加UUID
            product.setProductNum(ProductUtils.Date2String(product.getDepartureTime()) + product.getId());

            int priceI = random.nextInt(10000);

            double price = random.nextDouble();
            price = price+priceI+2000;
            product.setProductPrice(price);

            //设置描述
            product.setProductDesc(citys[from] + "---" + citys[to] + "---" + price);

            int status = random.nextInt(2);
            product.setProductStatus(status);
            productDao.save(product);
           // int j = status/0;
        }
    }



}
