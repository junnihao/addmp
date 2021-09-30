package com.example.demo.system.security.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DemoUserInfoDTO implements Serializable {
     private String username ;
     private String password ;

     public DemoUserInfoDTO(String username,String password){
          this.username = username ;
          this.password = password ;
     }
}
