package com.wiscom.hence.dao;

import com.wiscom.hence.model.LonLatData;
import java.util.List;

public interface LonLatDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LonLatData record);

    LonLatData selectByPrimaryKey(Long id);

    List<LonLatData> selectAll();

    int updateByPrimaryKey(LonLatData record);
}