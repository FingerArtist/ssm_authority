package ssm_authority.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_authority.dao.SysLogDao;
import ssm_authority.domain.SysLog;
import ssm_authority.service.SysLogService;

import java.util.List;

/**
 * @Description
 * @Author dzh
 * @date 2020/9/2 16:46
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer pageNum, Integer limit) {

        PageHelper.startPage(pageNum,limit);
       return sysLogDao.findAll();
    }
}
