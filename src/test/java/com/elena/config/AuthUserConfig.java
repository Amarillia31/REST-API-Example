package com.elena.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:auth-user.properties")

public interface AuthUserConfig extends Config{
// login credentials
    String username();
    String password();

//Auth user config
    String firstname();
    String lastname();
    Integer totalprice();
    Boolean depositpaid();
    String checkin();
    String checkout();
    String additionalneeds();
}
