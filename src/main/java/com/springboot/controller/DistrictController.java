package com.springboot.controller;

import com.springboot.entity.District;
import com.springboot.service.IDistrictService;
import com.springboot.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    IDistrictService service;
    @GetMapping("/")
    public JsonResult<List<District>> listByParent(String parent){
        List<District> list = service.listByParent(parent);
        return new JsonResult<>(SUCCESS,list);
    }
}
