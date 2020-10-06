package ssm_authority.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @Author dzh
 * @date 2020/9/5 11:29
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUpLoadController {


    public ModelAndView pictureUpLoad(){
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}
