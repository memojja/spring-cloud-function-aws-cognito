package com.allegory.users.controller;

import java.util.function.Function;

import com.allegory.users.model.NumberStatus;
import com.allegory.users.service.AmazonCognitoConnector;
import com.allegory.users.model.NumberCheck;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserController implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private final AmazonCognitoConnector amazonCognitoConnector;

	@Autowired
	public UserController(AmazonCognitoConnector amazonCognitoConnector) {
		this.amazonCognitoConnector = amazonCognitoConnector;
	}

	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent input) {
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setStatusCode(HttpStatus.SC_OK);
		responseEvent.setBody(amazonCognitoConnector.checkNumber("8965465").toString());
		return responseEvent;
	}

}
