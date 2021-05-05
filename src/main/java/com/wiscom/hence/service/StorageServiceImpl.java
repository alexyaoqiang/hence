package com.wiscom.hence.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(StorageServiceImpl.class);

   // @Resource
  //  private BusEnergyAlarmMapper busEnergyAlarmMapper;

    @Override
    public void storageBusEnergyAlarm(Map map) {
        List list= (List) map.get("list");
        if (list.size()>0) {
//            busEnergyAlarmMapper.deleteAllTodayFault();
       //     busEnergyAlarmMapper.batchInsert(map);
            log.info("新能源车辆告警批量插入数据,{}",list.size());
        }else {
            log.info("新能源车辆告警批量插入数据,{}",list.size());
        }

    }
}
