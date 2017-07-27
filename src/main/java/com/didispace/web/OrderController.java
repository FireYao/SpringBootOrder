package com.didispace.web;


import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/order")
public class OrderController {


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


}
