package ssm_authority.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_authority.domain.SysLog;
import ssm_authority.service.SysLogService;

import java.util.List;

/**
 * @Description 记录访问日志
 * @Author dzh
 * @date 2020/9/5 10:18
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping(value = "/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1") Integer pageNum
            , @RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<SysLog> logList = sysLogService.findAll(pageNum,pageSize);

        PageInfo<SysLog> pageInfo = new PageInfo<>(logList);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

}
