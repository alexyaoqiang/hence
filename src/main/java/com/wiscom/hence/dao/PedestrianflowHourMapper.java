package com.wiscom.hence.dao;

import com.wiscom.hence.model.PedestrianflowHour;
import java.util.List;

public interface PedestrianflowHourMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PedestrianflowHour record);

    PedestrianflowHour selectByPrimaryKey(Long id);

    List<PedestrianflowHour> selectAll();

    int updateByPrimaryKey(PedestrianflowHour record);
}