package ssm_authority.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm_authority.domain.SysLog;
import ssm_authority.service.SysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description
 * @Author dzh
 * @date 2020/9/2 15:48
 */
@Component
@Aspect
public class SysLogAspect {

    //注入的request代表当前的访问
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;

    private Class classz;

    private Method method;

    private SysLog sysLog;


    /**
     * @Description 日志记录前置通知  获取访问时间,访问方法,访问的类
     * @param  jp  被拦截到的方法
     * @return void
     * @date 2020/9/2 15:49
     */
    @Before("execution(* ssm_authority.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取访问开始时间
        visitTime = new Date();
        //获取当前访问的类
       classz =  jp.getTarget().getClass();
        //获取方法名
        String name = jp.getSignature().getName();

        //获取方法的参数
        Object[] args = jp.getArgs();

        //无参数方法
        if (args == null || args.length == 0){

            method = classz.getMethod(name);
        }else{ //有参
            //反射获取 method对象有参数方法
            Class[] classes = new Class[args.length];
            for (int i = 0;i<args.length;i++){
                classes[i] = args[i].getClass();
            }
             method = classz.getMethod(name, classes);
        }

    }

    /**
     * @Description  日志的后置通知
     * @param jp
     * @return void
     * @date 2020/9/2 16:17
     */
    @After("execution(* ssm_authority.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

         sysLog = new SysLog();
         sysLog.setVisitTime(visitTime);
        //获取方法执行的时间
      long executionTime =  System.currentTimeMillis() - visitTime.getTime();
        sysLog.setExecutionTime(executionTime);

      //获取类上的requestMapping注解对象
        RequestMapping classAnnotation = (RequestMapping)classz.getAnnotation(RequestMapping.class);
        RequestMapping methodAnnotation = null;
        if (classAnnotation != null){
            //获取方法上的requestMapping
            methodAnnotation = method.getAnnotation(RequestMapping.class);
        }
        // url 等于类上访问地址加上方法上的访问地址
        String url = classAnnotation.value()[0]+methodAnnotation.value()[0];
        sysLog.setUrl(url);

        //获取 springSecurity的用户名
        // 可以通过securityContext获取，也可以从request.getSession中获取
        SecurityContext context = SecurityContextHolder.getContext();
        // request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
        String username = ((User)
                (context.getAuthentication().getPrincipal())).getUsername();
        this.sysLog.setUsername(username);
        this.sysLog.setMethod("[类名]" + classz.getName() + "[方法名]" +
                classz.getName());

        //获取ip地址
        String remoteAddr = request.getRemoteAddr();
        sysLog.setIp(remoteAddr);

        sysLog.setMethod(method.getName());

        //存入数据库
        sysLogService.save(sysLog);

    }

}
