package com.muppet.auth.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 *
 */
@ControllerAdvice(basePackages = "com.muppet.auth.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}