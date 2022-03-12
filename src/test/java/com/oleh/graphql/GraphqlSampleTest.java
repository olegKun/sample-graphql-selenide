package com.oleh.graphql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import com.oleh.graphql.api.model.Users;
import com.oleh.graphql.api.service.GraphqlApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;

public class GraphqlSampleTest {
    private final GraphqlApiService service = new GraphqlApiService(
            new GraphqlClient("https://api.spacex.land/graphql/"));
    String query = "{\n"
            + "  company {\n"
            + "    ceo\n"
            + "  }\n"
            + "}\n";

    @Test
    public void testGraphQl() {
//        Map<String, Integer> params = new HashMap<>();
//        params.put("limit", 1);
//        List<Users> users = service.getUsers(params);
//        assertThat(users,hasSize(1));
    }
}
