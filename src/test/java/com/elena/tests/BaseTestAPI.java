package com.elena.tests;

import com.elena.config.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestAPI {

    @BeforeAll
    static void StartUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class,System.getProperties());
        RestAssured.baseURI = config.getBaseUri();
    }
}
