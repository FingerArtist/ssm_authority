package ssm_authority.controller;

import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_authority.domain.Orders;
import ssm_authority.service.OrdersService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    /**
     * 分页查找订单表
     * @param pageNum
     * @param pageSize
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/7 13:21
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize) throws Exception{

        ModelAndView mv = new ModelAndView();
        //找到订单
        List<Orders> all = ordersService.findAll(pageNum, pageSize);
        //交给pageHelper封装
        PageInfo<Orders> ordersList = new PageInfo<>(all);
        //添加到视图展示
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }

    /**
     * 查找订单详情
     * @param ordersId
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/7 16:01
     */
    @RequestMapping("/findDetailById")
    public ModelAndView findDetailById(@RequestParam(required = true,name = "ordersId") String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders= ordersService.findDetailById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }


}
