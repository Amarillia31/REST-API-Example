package com.restfulbooker.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:data/auth-user.properties")

public interface AuthUserConfig extends Config {
// login credentials
    String username();
    String password();

//Auth user config
    String firstName();
    String lastName();
    Integer totalPrice();
    Boolean depositPaid();
    String checkIn();
    String checkOut();
    String additionalNeeds();
}
