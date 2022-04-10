package com.oleh.graphql;

import static com.oleh.graphql.util.GqlUtils.readGql;

import com.oleh.graphql.api.response.GraphqlResponse;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.oleh.graphql.model.GraphqlQuery;

public class GraphqlClient {
    private final String url;
    private final RequestSpecification specification = RestAssured.given()
            .contentType(ContentType.JSON)
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    public GraphqlClient(String url) {
        this.url = url;
    }

    public GraphqlResponse execute(String fileName, Object variables) {
        GraphqlQuery query = readGql(fileName,variables);
        return new GraphqlResponse(specification
                .body(query)
                .when()
                .post(url));
    }
}
