package com.wiscom.hence.dao;

import com.wiscom.hence.model.PlaceOriginOut;
import java.util.List;

public interface PlaceOriginOutMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlaceOriginOut record);

    PlaceOriginOut selectByPrimaryKey(Long id);

    List<PlaceOriginOut> selectAll();

    int updateByPrimaryKey(PlaceOriginOut record);
}