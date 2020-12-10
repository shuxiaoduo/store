package com.example.store.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Constant {

    public static final GrantedAuthority GLOBAL_ADMIN = new SimpleGrantedAuthority("admin");

}
