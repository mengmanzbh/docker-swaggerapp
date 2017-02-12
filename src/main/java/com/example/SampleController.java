package com.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
import java.util.*;
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello World!";
    }

    @RequestMapping("/news")  
    @ResponseBody
    public String news() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("key1", 1);
        map2.put("key2", 2);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        list.add(map);
        list.add(map2);
        String jsonString = JSON.toJSONString(list);
         return jsonString;
    }  

 

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}