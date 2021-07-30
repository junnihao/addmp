package com.example.demo.system.parameter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import java.util.Map;

//import java.util.Map;

@Getter
@Setter
@ToString
public class UserLoginParameter {
    /*名字要与前台vue传进来的参数名字一样*/
    private String username ;
    private String password ;

    //private Map<String,String> mapPamater;
}
