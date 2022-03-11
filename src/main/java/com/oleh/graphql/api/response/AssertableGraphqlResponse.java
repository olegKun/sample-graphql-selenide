package com.oleh.graphql.api.response;

import io.restassured.response.Response;

import java.util.List;
import org.hamcrest.Matcher;
import com.fasterxml.jackson.annotation.JsonRootName;

public class AssertableGraphqlResponse {
    private Response response;

    public AssertableGraphqlResponse(Response response) {
        this.response = response;
    }

    public AssertableGraphqlResponse body(String jsonPath, Matcher<?> matcher){
        response.then().body(jsonPath,matcher);
        return this;
    }

    public <T> T asPogo(Class<T> tClass){
        return response.then().extract().jsonPath().getObject(getJsonPath(tClass), tClass);
    }

    private String getJsonPath(Class<?> tClass) {
        JsonRootName rootName = tClass.getAnnotation(JsonRootName.class);
        String name = "data";
        if (rootName!=null){
            name+= "."+rootName.value();
        } else {
            name += "." + tClass.getSimpleName().toLowerCase();
        }
        return name;
    }

    public <T> List<T> getList(Class<T> tClass) {
        return response.then().extract().jsonPath().getList(getJsonPath(tClass), tClass);
    }
}
