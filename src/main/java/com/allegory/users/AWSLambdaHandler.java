package com.allegory.users;

import com.allegory.users.model.Number;
import com.allegory.users.model.NumberStatus;
import com.allegory.users.service.AmazonCognitoConnector;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AWSLambdaHandler implements RequestHandler<Number, NumberStatus> {

    @Override
    public NumberStatus handleRequest(Number number, Context context) {
        return  new AmazonCognitoConnector().checkNumber(number.getNumber());
    }
}
