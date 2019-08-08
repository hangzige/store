package com.springboot.controller;

import com.springboot.controller.BaseController;
import com.springboot.entity.Address;
import com.springboot.service.IAddressService;
import com.springboot.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 用户信息控制类
 */

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

    @Autowired
    IAddressService service;
    @RequestMapping("create_address")
    public JsonResult<Void> createAddress(HttpSession session, Address address){
        //从session中获取uid和用户名
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //执行添加地址信息方法
        service.createAddress(uid,username,address);
        return new JsonResult<Void>(SUCCESS);
    }
}
