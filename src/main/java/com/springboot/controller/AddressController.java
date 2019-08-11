package com.springboot.controller;

import com.springboot.entity.Address;
import com.springboot.service.IAddressService;
import com.springboot.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("list")
    public JsonResult<List<Address>> listByUid(HttpSession session){
        Integer uid=getUidFromSession(session);
        List<Address> data=service.listByUid(uid);
        return new JsonResult<>(SUCCESS,data);
    }
}
