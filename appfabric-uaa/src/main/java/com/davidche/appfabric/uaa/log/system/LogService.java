package com.davidche.appfabric.uaa.log.system;

import com.davidche.appfabric.uaa.log.system.model.Log;
import org.springframework.stereotype.Component;

@Component
public class LogService {

    public int createLog(Log log) {
        //return this.logDao.insertSelective(log);
        System.out.println("模拟日志入库" + log);
        return 1;
    }

    public int updateLog(Log log) {
        //return this.logDao.updateByPrimaryKeySelective(log);
        System.out.println("模拟日志更新" + log);
        return 1;
    }

}
