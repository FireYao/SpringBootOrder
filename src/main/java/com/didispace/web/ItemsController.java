package com.didispace.web;

import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 火尧 on 2017/7/25.
 */

@Controller
@Api(value = "商品")
public class ItemsController {

    @Resource
    private ItemService itemService;

    @ApiIgnore
    @GetMapping(value = "/")
    public String index(ModelMap map) {
        map.addAttribute("hello", "hello Thymeleaf");
        return "index";
    }


    @ApiOperation(value = "商品页面")
    @GetMapping("/itemsNg")
    public String items() {
        return "items";
    }

    @ApiOperation(value = "商品列表")
    @GetMapping("/items")
    @ResponseBody
    public Object itemList() {
        List<Item> items = itemService.getItems();
        return items;
    }

}
