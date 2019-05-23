package com.scsc.rbac.service.serviceimpl;

import com.scsc.rbac.entity.Log;
import com.scsc.rbac.mapper.LogMapper;
import com.scsc.rbac.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qing
 * @date 2019/5/7 15:23
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public void addLog(Log log) {
        logMapper.addLog(log);
    }
}
