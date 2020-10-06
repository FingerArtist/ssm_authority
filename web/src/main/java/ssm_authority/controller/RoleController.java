package ssm_authority.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_authority.domain.Role;
import ssm_authority.service.RoleService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查找所有角色
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/24 13:58
     */

    @RequestMapping("/findAll")
    @RolesAllowed( value = {"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();

        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }
    /**
     * 添加角色
     * @param role
     * @return java.lang.String
     * @date 2020/7/24 13:58
     */

    @RequestMapping("/save")
    @RolesAllowed("ROLE_ADMIN")
    public String save(Role role)throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    /**
     * 角色详情页面
     * @param id
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/24 14:01
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role =  roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }


}
