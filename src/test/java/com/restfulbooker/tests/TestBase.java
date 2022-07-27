package com.restfulbooker.tests;

import com.restfulbooker.config.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void StartUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class,System.getProperties());
        RestAssured.baseURI = config.getBaseUri();
    }
}
