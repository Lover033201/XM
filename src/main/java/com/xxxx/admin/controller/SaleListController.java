package com.xxxx.admin.controller;

import com.xxxx.admin.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sale")
public class SaleListController extends BaseController {
    @RequestMapping("index")
    public String index(){
        return "sale/sale";
    }
}
