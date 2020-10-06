package ssm_authority.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_authority.domain.Product;
import ssm_authority.service.ProductService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
   // @Autowired
    //@Qualifier("productService")
    private ProductService productService;
    /**
     * 查找所有商品列表
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/3 11:11
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1") Integer pageNum
    , @RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        //查找到指定的结果
        List<Product> productList = productService.findAll(pageNum,pageSize);
        //将结果交给pageInfo
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    /**
     * 添加产品
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/3 12:11
     */
    @RequestMapping("/save")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "forward:/product/findAll";
    }

    /**
     * 随机生成产品
     * @param product
     * @return java.lang.String
     * @date 2020/7/4 12:09
     */
    @RequestMapping("/saveRandom")
    public String saveRandom(Product product) throws Exception{
        productService.saveRandom();
        return "forward:/product/findAll";
    }


}
