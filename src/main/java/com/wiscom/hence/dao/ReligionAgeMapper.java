package com.wiscom.hence.dao;

import com.wiscom.hence.model.ReligionAge;
import java.util.List;

public interface ReligionAgeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReligionAge record);

    ReligionAge selectByPrimaryKey(Long id);

    List<ReligionAge> selectAll();

    int updateByPrimaryKey(ReligionAge record);
}