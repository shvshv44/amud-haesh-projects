package org.shaq.plugins.aaashaqtest;

import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class TestMain {


    private final static String schema1 = "type Tweet {\n" +
            "    id: ID!\n" +
            "    # The tweet text. No more than 140 characters!\n" +
            "    body: String\n" +
            "    # When the tweet was published\n" +
            "    date: Date\n" +
            "    # Who published the tweet\n" +
            "    Author: User\n" +
            "    # Views, retweets, likes, etc\n" +
            "    Stats: Stat\n" +
            "}\n" +
            "\n" +
            "type User {\n" +
            "    id: ID!\n" +
            "    username: String\n" +
            "    first_name: String\n" +
            "    last_name: String\n" +
            "    full_name: String\n" +
            "    name: String @deprecated\n" +
            "    avatar_url: Url\n" +
            "}\n" +
            "\n" +
            "type Stat {\n" +
            "    views: Int\n" +
            "    likes: Int\n" +
            "    retweets: Int\n" +
            "    responses: Int\n" +
            "}\n" +
            "\n" +
            "type Notification {\n" +
            "    id: ID\n" +
            "    date: Date\n" +
            "    type: String\n" +
            "}\n" +
            "\n" +
            "type Meta {\n" +
            "    count: Int\n" +
            "}\n" +
            "\n" +
            "scalar Url\n" +
            "scalar Date\n" +
            "\n" +
            "type Query {\n" +
            "    Tweet(id: ID!): Tweet\n" +
            "    Tweets(limit: Int, skip: Int, sort_field: String, sort_order: String): [Tweet]\n" +
            "    TweetsMeta: Meta\n" +
            "    User(id: ID!): User\n" +
            "    Notifications(limit: Int): [Notification]\n" +
            "    NotificationsMeta: Meta\n" +
            "}\n" +
            "\n" +
            "type Mutation {\n" +
            "    createTweet (\n" +
            "        body: String\n" +
            "    ): Tweet\n" +
            "    deleteTweet(id: ID!): Tweet\n" +
            "    markTweetRead(id: ID!): Boolean\n" +
            "}";

    private final static String schema2 = "schema {\n" +
            "  query: Query\n" +
            "  mutation: Mutation\n" +
            "}";


    public static void main(String[] args) {

        SchemaParser parser = new SchemaParser();
        TypeDefinitionRegistry registry1 = parser.parse(schema1);
        TypeDefinitionRegistry registry2 = parser.parse(schema2);
        System.out.println(registry1);

    }



}
