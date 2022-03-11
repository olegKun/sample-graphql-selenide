package com.oleh.graphql.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import com.oleh.graphql.model.GraphqlQuery;

public class GqlUtils {
    public static GraphqlQuery gql(String query){
        return new GraphqlQuery(query);
    }

    public static GraphqlQuery gql(String query, Object variables){
        return new GraphqlQuery(query,variables);
    }

    private static String readFile(String name){
        URL url = GqlUtils.class.getClassLoader().getResource(name);
        File file = new File(Objects.requireNonNull(url).getFile());

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static GraphqlQuery readGql(String fileName){
        return gql(readFile(fileName));
    }

    public static GraphqlQuery readGql(String fileName, Object variables) {
        return gql(readFile(fileName),variables);
    }
}
