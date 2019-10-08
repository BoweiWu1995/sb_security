package com.mydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author wuwei
 * @create 2019/10/8 10:43
 * @desc
 **/
@RestController
public class AuthController {

    /* 只有角色one 才能访问*/
    @Secured("ROLE_ONE")
    @GetMapping("/one")
    public String one(){
        return "auth/one";

    }

    /* 只有角色two 才能访问*/
    @PreAuthorize("hasRole('TWO')")
    @GetMapping("/two")
    public String two(){
        return "auth/two";

    }

    /* 只有角色three 才能访问*/
    @RolesAllowed("THREE")
    @GetMapping("/three")
    public String three(){
        return "auth/three";

    }

}
