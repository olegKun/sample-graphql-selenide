package com.oleh.graphql.api.service;

import com.oleh.graphql.api.model.Users;

import java.util.List;
import java.util.Map;
import com.oleh.graphql.GraphqlClient;

public class GraphqlApiService {
    private GraphqlClient client;

    public GraphqlApiService(GraphqlClient client) {
        this.client = client;
    }

    public List<Users> getUsers(Map<?,?> params){
        return client.execute("users.gql", params)
                .then()
                .getList(Users.class);
    }
}
