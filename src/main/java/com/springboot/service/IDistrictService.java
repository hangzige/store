package com.springboot.service;

import com.springboot.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父级查找子级城市
     * @param parent 父级
     * @return
     */
    List<District> listByParent(String parent);

    /**
     * 根据地区编号查询地区信息
     * @param code 地区编号
     * @return 地区信息
     */
    District getByCode(String code);
}
