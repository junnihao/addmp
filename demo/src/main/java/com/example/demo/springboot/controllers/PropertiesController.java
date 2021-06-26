package com.example.demo.springboot.controllers;

import com.example.demo.springboot.properties.NeoProperties;
import com.example.demo.springboot.properties.PropertiesFromAssignPath;
import com.example.demo.springboot.properties.PropertiesFromClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class PropertiesController {
    @Autowired
    NeoProperties np ;
    @Autowired
    PropertiesFromClassPath pm ;

    @Autowired
    PropertiesFromAssignPath pm2 ;

    @Value("${com.neo.title}")
    String test ;

    @RequestMapping("/properties")
    public String index() {
        System.out.println("  >>>>>>>>>>>>>  " +np.getDescription()) ;
        System.out.println("  >>>>>>>>>>>>> PropertiesFromClassPath  " +pm.getUpgradeFromXAtoXBEN()) ;
        System.out.println("  >>>>>>>>>>>>> PropertiesFromAssignPath  " +pm2.getUpgradeFromXAtoXBEN()) ;
        System.out.println("  >>>>>>>>>>>>>  " +test) ;
        return "Hello World";
    }
}
