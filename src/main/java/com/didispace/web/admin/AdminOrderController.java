package com.didispace.web.admin;

import com.didispace.domain.Order;
import com.didispace.service.OrderService;
import com.didispace.tools.RestResultGenerator;
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
import java.util.stream.Collectors;

@Controller
@Api(value = "订单后台管理")
@RequestMapping("/adminOrder")
public class AdminOrderController {


    private final static Sort sort = new Sort(Sort.Direction.ASC, "orderId");

    @Resource
    private OrderService orderService;


    @PutMapping
    @ApiOperation(value = "订单发货和关闭订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "stauts", value = "订单状态", required = true, dataType = "Integer")
    })
    @ResponseBody
    public Object sendOrClose(@RequestParam Integer orderId, @RequestParam Integer stauts) {
        try {
            orderService.sendOrCloseOrder(orderId, stauts);
            return RestResultGenerator.genResult(null, "修改成功");
        } catch (Exception e) {
            return RestResultGenerator.genResult(null, "系统异常", false);
        }
    }

    @GetMapping
    @ApiOperation(value = "订单管理分页列表", notes = "默认一页5条记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "Integer")
    })
    public String adminOrder(ModelMap map,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) throws Exception {

        Pageable pageable = new PageRequest(page - 1, size, sort);

        Page<Order> orderPage = orderService.findAllOnlyOrder(pageable);
        map.addAttribute("orders", orderPage.getContent().stream()
                .sorted((o1, o2) -> o1.getOrderId() - o2.getOrderId()).collect(Collectors.toList()));
        map.addAttribute("totalPage", orderPage.getTotalPages());
        map.addAttribute("totalElements", orderPage.getTotalElements());
        map.addAttribute("page", page);
        return "orderMng";
    }

}
