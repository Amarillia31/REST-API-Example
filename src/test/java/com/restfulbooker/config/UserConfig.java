package com.restfulbooker.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:data/user.properties")

public interface UserConfig extends Config {
// login credentials

    String username();
    String password();

//user1 config
    String firstName();
    String lastName();
    Integer totalPrice();
    Boolean depositPaid();
    String checkIn();
    String checkOut();
    String additionalNeeds();
}
