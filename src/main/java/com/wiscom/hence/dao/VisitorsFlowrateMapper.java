package com.wiscom.hence.dao;

import com.wiscom.hence.model.VisitorsFlowrate;
import java.util.List;

public interface VisitorsFlowrateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VisitorsFlowrate record);

    VisitorsFlowrate selectByPrimaryKey(Long id);

    List<VisitorsFlowrate> selectAll();

    int updateByPrimaryKey(VisitorsFlowrate record);
}