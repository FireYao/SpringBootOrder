package com.simpletour.web;


import com.simpletour.domain.Order;
import com.simpletour.domain.OrderItem;
import com.simpletour.exception.ItemStockNotEnoughException;
import com.simpletour.tools.PageResult;
import com.simpletour.service.OrderService;
import com.simpletour.tools.RestResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
@Api(value = "订单信息")
public class OrderController {

    private final static Sort ID_SORT = new Sort(Sort.Direction.ASC, "orderId");

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
        try {
            return RestResultGenerator.genResult(orderService.findById(orderId), "");
        } catch (Exception e) {
            return RestResultGenerator.genResult(null, "系统异常", false);
        }
    }

    @PostMapping
    @ApiOperation(value = "下单", notes = "根据订单明细列表(JSON数组)创建订单")
    @ApiImplicitParam(name = "items", value = "订单明细列表", required = true, dataType = "List<OrderItem>")
    @ResponseBody
    public Object createOrder(@RequestBody List<OrderItem> items) {
        if (items == null || items.size() < 1) {
            return RestResultGenerator.genResult(null, "未选中任何商品", false);
        }

        try {
            orderService.createOrder(items);
            return RestResultGenerator.genResult(null, "下单成功");
        } catch (ItemStockNotEnoughException ie) {
            return RestResultGenerator.genResult(null, ie.getMessage(), false);
        } catch (Exception e) {
            return RestResultGenerator.genResult(null, "系统异常", false);
        }
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


        try {
            Pageable pageable = new PageRequest(page - 1, size, ID_SORT);

            Page<Order> all = orderService.findAll(pageable);

            PageResult<Order> result = new PageResult();

            result.setList(all.getContent());
            result.setPage(page);
            result.setTotalElements(all.getTotalElements());
            result.setTotalPage(all.getTotalPages());

            return RestResultGenerator.genResult(result, "");

        } catch (Exception e) {
            return RestResultGenerator.genResult(null, "系统异常", false);
        }

    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改订单状态")
    @ApiImplicitParam(name = "stauts", value = "订单的状态", required = true, dataType = "Integer")
    public Object updateOrder(@RequestParam Integer orderId, @RequestParam Integer stauts) {
        //提醒发货
        if (stauts == 2) {
            return RestResultGenerator.genResult(null, "提醒成功");
        }
        try {
            orderService.updateStauts(orderId, stauts);
            return RestResultGenerator.genResult(null, "修改成功");
        } catch (Exception e) {
            return RestResultGenerator.genResult(null, "系统异常");
        }
    }

}
