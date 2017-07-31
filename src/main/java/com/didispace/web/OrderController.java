package com.didispace.web;


import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import com.didispace.domain.OrderItem;
import com.didispace.domain.PageResult;
import com.didispace.rabbit.Sender;
import com.didispace.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
@Api(value = "订单信息")
public class OrderController {


    @Autowired
    private Sender sender;

    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    @ApiOperation(value = "订单页面")
    public String orderPage() {
        return "order";
    }


    @GetMapping("/info/{orderId}")
    @ApiOperation(value = "订单详情页面")
    @ApiImplicitParam(name = "orderId", value = "订单编号orderId", required = true, dataType = "Integer")
    public String orderInfoPage(@PathVariable("orderId") Integer orderId, ModelMap map) {

        System.out.println(orderId);
        map.addAttribute("orderId", orderId);

        return "orderinfo";
    }

    @ApiOperation(value = "订单明细")
    @ApiImplicitParam(name = "orderId", value = "订单编号orderId", required = true, dataType = "Integer")
    @GetMapping("/{orderId}")
    @ResponseBody
    public Object orderInfo(@PathVariable("orderId") Integer orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping
    @ApiOperation(value = "下单", notes = "根据订单明细列表(JSON数组)创建订单")
    @ApiImplicitParam(name = "items", value = "订单明细列表", required = true, dataType = "List<OrderItem>")
    @ResponseBody
    public Object createOrder(@RequestBody List<OrderItem> items) {
        if (items == null || items.size() < 1) {
            return "未选中任何商品";
        }

        System.out.println(JSONObject.toJSONString(items));
        orderService.createOrder(items);
        return "success";
    }

    @ApiOperation(value = "所有订单分页", notes = "默认一页5条记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "Integer")
    })
    @GetMapping
    @ResponseBody
    public Object findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size) {

        Pageable pageable = new PageRequest(page - 1, size);

        Page<Order> all = orderService.findAll(pageable);

        PageResult<Order> result = new PageResult();

        result.setList(all.getContent().stream()
                .sorted((o1, o2) -> o1.getOrderId() - o2.getOrderId()).collect(Collectors.toList()));
        result.setPage(page);
        result.setTotalElements(all.getTotalElements());
        result.setTotalPage(all.getTotalPages());

//        List<Order> all = orderService.findAll();
        return result;
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改订单状态")
    @ApiImplicitParam(name = "stauts", value = "订单的状态", required = true, dataType = "Integer")
    public Object updateOrder(@RequestParam Integer orderId, @RequestParam Integer stauts) {
        //提醒发货
        if (stauts == 2) {
            sender.send("订单" + orderId + "提醒发货");
            return 2;
        }
        orderService.updateStauts(orderId, stauts);
        return "success";
    }

}
