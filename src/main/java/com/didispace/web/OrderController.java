package com.didispace.web;


import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import com.didispace.domain.OrderItem;
import com.didispace.domain.PageResult;
import com.didispace.service.OrderService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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


    @GetMapping("/info/{orderId}")
    @ApiIgnore
    public String orderInfoPage(@PathVariable("orderId") Integer orderId, ModelMap map) {

        System.out.println(orderId);
        map.addAttribute("orderId", orderId);

        return "orderinfo";
    }


    @PostMapping
    @ApiOperation(value = "下单")
    @ResponseBody
    public Object createOrder(@RequestBody List<OrderItem> items) {
        if (items == null || items.size() < 1) {
            return "未选中任何商品";
        }

        System.out.println(JSONObject.toJSONString(items));
        orderService.createOrder(items);
        return "success";
    }

    @ApiOperation(value = "所有订单")
    @GetMapping
    @ResponseBody
    public Object findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size) {

        Pageable pageable = new PageRequest(page - 1, size);

        Page<Order> all = orderService.findAll(pageable);

        PageResult<Order> result = new PageResult();

        result.setList(all.getContent());
        result.setPage(page);
        result.setTotalElements(all.getTotalElements());
        result.setTotalPage(all.getTotalPages());

//        List<Order> all = orderService.findAll();
        return result;
    }

    @ApiOperation(value = "订单详情")
    @GetMapping("/{orderId}")
    @ResponseBody
    public Object orderInfo(@PathVariable("orderId") Integer orderId) {
        return orderService.findById(orderId);
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改订单状态")
    public Object updateOrder(@RequestParam Integer orderId, @RequestParam Integer stauts) {

        System.out.println(orderId + ":::" + stauts);

        orderService.updateStauts(orderId, stauts);
        if (stauts == 2)
            return "2";
        return "success";
    }

}
