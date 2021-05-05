package com.wiscom.hence.dao;

import com.wiscom.hence.model.PedestrianflowDay;
import java.util.List;

public interface PedestrianflowDayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PedestrianflowDay record);

    PedestrianflowDay selectByPrimaryKey(Long id);

    List<PedestrianflowDay> selectAll();

    int updateByPrimaryKey(PedestrianflowDay record);
}