package com.scsc.rbac.mapper;

import com.scsc.rbac.entity.Log;
import org.springframework.stereotype.Repository;

/**
 * @author qing
 * @date 2019/5/7 15:25
 */
@Repository
public interface LogMapper {
    /**
     * 登陆日志放入数据库中
     * @param log
     */
    void addLog(Log log);
}
