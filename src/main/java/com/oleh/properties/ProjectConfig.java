package com.oleh.properties;

import org.aeonbits.owner.Config;

@Config.Sources(
        {
//                "file:ProjectConfig.properties",
        "classpath:ProjectConfig.properties"
        })
public interface ProjectConfig extends Config{

    @Config.Key("project.baseUrl")
    String baseUrl();
}
