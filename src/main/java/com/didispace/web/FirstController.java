package com.didispace.web;

import com.didispace.domain.Item;
import com.didispace.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 火尧 on 2017/7/25.
 */

@Controller
public class FirstController {

    @Resource
    private ItemService itemService;

    @ApiIgnore
    @GetMapping(value = "/")
    public String index(ModelMap map) {
        map.addAttribute("hello", "hello Thymeleaf");

        return "index";
    }

    @ApiIgnore
    @GetMapping("/itemList")
    public String itemList(ModelMap map) {
        List<Item> items = itemService.getItems();
        map.addAttribute("itemList", items);

        return "itemList";
    }

}
