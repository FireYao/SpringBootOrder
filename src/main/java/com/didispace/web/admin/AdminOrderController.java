package com.didispace.web.admin;

import com.didispace.domain.Order;
import com.didispace.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController {


    @Resource
    private OrderService orderService;


    /*@GetMapping
    public String adminOrder(ModelMap map) {
        List<Order> order = orderService.findAllOnlyOrder();
        map.addAttribute("orders", order);
        return "orderMng";
    }*/

    @PutMapping
    @ResponseBody
    public Object adminOrder(@RequestParam Integer orderId, @RequestParam Integer stauts) {
        orderService.sendOrCloseOrder(orderId, stauts);
        return "success";
    }

    @GetMapping
    public String adminOrder(ModelMap map,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {

        Pageable pageable = new PageRequest(page - 1, size);

        Page<Order> orderPage = orderService.findAllOnlyOrder(pageable);
        map.addAttribute("orders", orderPage.getContent());
        map.addAttribute("totalPage", orderPage.getTotalPages());
        map.addAttribute("totalElements", orderPage.getTotalElements());
        map.addAttribute("page", page);
        return "orderMng";
    }

}
