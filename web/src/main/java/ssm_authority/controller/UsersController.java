package ssm_authority.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_authority.domain.Role;
import ssm_authority.domain.UserInfo;
import ssm_authority.service.UsersService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    /**
     * 查找所有用户
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/13 13:42
     */
    @RequestMapping("/findAll")
    @RolesAllowed(value = {"ROLE_ADMIN"})
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();

        List<UserInfo> userInfoList = usersService.findAll();

        mv.addObject("userList",userInfoList);

        mv.setViewName("user-list");
        return mv;
    }
    /**
     * 增加用户
     * @param userInfo
     * @return java.lang.String
     * @date 2020/7/13 15:20
     */
    @RequestMapping("/save")
    public String save( UserInfo userInfo)throws Exception{

        usersService.save(userInfo);

        return "forward:/users/findAll";
    }
    /**
     * 查询用户以及用户可以添加的角色
     * @param userid
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/13 15:20
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = usersService.findById(userid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = usersService.findOtherRoles(userid);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 用户详情页面
     * @param userId
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/13 16:52
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "userId",required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = usersService.findById(userId);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 给用户添加角色关联
     * @param userId
     * @param ids
     * @return org.springframework.web.servlet.ModelAndView
     * @date 2020/7/13 16:49
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] ids)throws Exception{
        ModelAndView mv = new ModelAndView();
        //添加用户的角色
        usersService.addRoles(userId,ids);

        return "redirect:/users/findAll";
    }

}
