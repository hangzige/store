package com.springboot.service.impl;

import com.springboot.entity.District;
import com.springboot.mapper.DistrictMapepr;
import com.springboot.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    DistrictMapepr mapper;

    @Override
    public List<District> listByParent(String parent) {
        return findByParent(parent);
    }

    @Override
    public District getByCode(String code){
        return findByCode(code);
    }
    private District findByCode(String code){
        return mapper.findByCode(code);
    }

    private List<District> findByParent(String parent){
            return mapper.findByParent(parent);
    }


}
