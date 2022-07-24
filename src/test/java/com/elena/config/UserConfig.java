package com.elena.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:data/user.properties")

public interface UserConfig extends Config{
// login credentials
    String username();
    String password();

//user1 config
    String firstname();
    String lastname();
    Integer totalprice();
    Boolean depositpaid();
    String checkin();
    String checkout();
    String additionalneeds();
}
