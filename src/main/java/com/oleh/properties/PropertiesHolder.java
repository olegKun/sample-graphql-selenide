package com.oleh.properties;

import org.aeonbits.owner.ConfigFactory;

public class PropertiesHolder {
    public static final ProjectConfig projectProperties =  ConfigFactory.create(ProjectConfig.class);
}
