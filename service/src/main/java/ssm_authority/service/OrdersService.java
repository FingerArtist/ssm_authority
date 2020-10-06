package ssm_authority.service;

import org.springframework.stereotype.Service;
import ssm_authority.domain.Orders;

import java.util.List;


public interface OrdersService  {
    List<Orders> findAll(Integer pageNum,Integer pageSize)throws Exception;

    Orders findDetailById(String ordersId)throws Exception;
}
