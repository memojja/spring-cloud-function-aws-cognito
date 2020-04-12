package com.allegory.users.constant;

public class CognitoConfig {

    public static int COGNITO_LIMIT_PER_REQ_USER = 60; // dont set
    public final static String AWS_ACCESS_KEY = System.getenv("ACCESS_KEY");
    public final static String AWS_SECRET_KEY = System.getenv("SECRET_KEY");
    public final static String AWS_USER_POOL_ID = System.getenv("USER_POOL_ID");
    public final static String AWS_REGION = System.getenv("REGION");

}
