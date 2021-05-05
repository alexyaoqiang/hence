package com.wiscom.hence.dao;

import com.wiscom.hence.model.PlaceOriginIn;
import java.util.List;

public interface PlaceOriginInMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlaceOriginIn record);

    PlaceOriginIn selectByPrimaryKey(Long id);

    List<PlaceOriginIn> selectAll();

    int updateByPrimaryKey(PlaceOriginIn record);
}