package com.wiscom.hence.dao;

import com.wiscom.hence.model.GenderAnalysis;
import java.util.List;

public interface GenderAnalysisMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GenderAnalysis record);

    GenderAnalysis selectByPrimaryKey(Long id);

    List<GenderAnalysis> selectAll();

    int updateByPrimaryKey(GenderAnalysis record);
}