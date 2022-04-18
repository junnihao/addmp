package com.example.demo.system.security.exception;

import org.springframework.security.core.AuthenticationException;

public class DemoAuthenticationException extends AuthenticationException {
    public DemoAuthenticationException(String msg) {
        super(msg);
    }
}
