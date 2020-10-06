package ssm_authority.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm_authority.domain.Permission;
import ssm_authority.service.PermissionService;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }
    /**
     * 添加权限许可
     * @param permission
     * @return java.lang.String
     * @date 2020/7/24 16:00
     */
    @RequestMapping("/save")
    public String save(Permission permission)throws Exception{
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }


}
