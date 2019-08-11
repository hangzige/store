package com.springboot.mapper;

import com.springboot.entity.District;

import java.util.List;

public interface DistrictMapepr {
    /**
     * 根据parent查询子级地区信息
     * @param parent 父级id
     * @return 子级地区信息
     */
    List<District> findByParent(String parent);

    /**
     * 根据地区编号查地区信息
     * @param code 地区编号
     * @return 地区信息
     */
    District findByCode(String code);

}
