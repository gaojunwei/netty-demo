package com.jd.jr.innovation.epl.ap.guard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: gaojunwei
 * @Date: 2018/11/13 15:06
 * @Description:
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 进入首页
     * @return
     */
    @RequestMapping(value ="/index",method = RequestMethod.GET)
    public String index() {
        logger.info("come in ...");
        return "index";
    }
}