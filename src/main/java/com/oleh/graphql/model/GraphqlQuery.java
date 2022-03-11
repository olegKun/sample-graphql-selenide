package com.oleh.graphql.model;

public class GraphqlQuery {
    private String query;
    private Object variables;

    public String getQuery() {
        return query;
    }

    public GraphqlQuery(String query, Object variables) {
        this.query = query;
        this.variables = variables;
    }

    public Object getVariables() {
        return variables;
    }


    public GraphqlQuery(String query) {
        this.query = query;
    }
}
