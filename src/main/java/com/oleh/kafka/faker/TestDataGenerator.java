package com.oleh.kafka.faker;

import java.util.HashMap;
import java.util.Map;
import org.jtwig.*;
public class TestDataGenerator {
    private JtwigModel model;

    public TestDataGenerator(){
        this.model = JtwigModel.newModel().with("faker", new TestDataFaker());
    }

    public String generate(String path){
        return generate(path,new HashMap<>());
    }

    public String generate(String path, Map<String,String> params){
        params.forEach((k,v)->model.with(k,v));
        JtwigTemplate template = JtwigTemplate.classpathTemplate(path);
        return template.render(model);
    }
}
