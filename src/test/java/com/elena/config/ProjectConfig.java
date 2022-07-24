package com.elena.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties"
})
public interface ProjectConfig extends Config{
    @Key("baseURI")
    @DefaultValue("https://restful-booker.herokuapp.com/")
    String getBaseUri();
}
