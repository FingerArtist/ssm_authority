package ssm_authority.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_authority.dao.OrdersDao;
import ssm_authority.domain.Orders;
import ssm_authority.service.OrdersService;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer pageNum,Integer pageSize) throws Exception {
        //使用pagehelper插件
        PageHelper.startPage(pageNum,pageSize);
        return ordersDao.findAll();
    }
    /**
     * 根据id查出订单详情
     * @param ordersId
     * @return ssm_authority.domain.Orders
     * @date 2020/7/7 15:58
     */
    @Override
    public Orders findDetailById(String ordersId) throws Exception {
        Orders detailById = ordersDao.findDetailById(ordersId);
        return detailById;
    }
}
