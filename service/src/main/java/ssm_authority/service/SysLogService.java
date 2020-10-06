package ssm_authority.service;

import ssm_authority.domain.SysLog;

import java.util.List;

public interface SysLogService {


    void save(SysLog sysLog)throws Exception;

    List<SysLog> findAll(Integer pageNum, Integer limit);
}
