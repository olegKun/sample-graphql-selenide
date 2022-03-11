package com.oleh.graphql.api.response;

import io.restassured.response.Response;

public class GraphqlResponse {
    private Response response;

    public GraphqlResponse(Response response) {
        this.response = response;
    }

    public AssertableGraphqlResponse then() {
        return new AssertableGraphqlResponse(response);
    }
}
