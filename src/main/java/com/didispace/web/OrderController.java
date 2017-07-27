package com.didispace.web;


import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.service.OrderService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    @ApiIgnore
    public String orderPage() {
        return "order";
    }


    @GetMapping("/info")
    @ApiIgnore
    public String orderInfoPage() {
        return "orderinfo";
    }


    @PostMapping()
    @ResponseBody
    public Object createOrder(@RequestBody String items){

        System.out.println(items);

        List<Item> items1 = JSONObject.parseArray(items, Item.class);

        System.out.println(JSONObject.toJSONString(items1));

        return "success";
    }
    @GetMapping()
    @ResponseBody
    public Object findAll(){
        return null;
    }


}
