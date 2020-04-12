package com.allegory.users.controller;

import java.util.function.Function;

import com.allegory.users.model.NumberStatus;
import com.allegory.users.service.AmazonCognitoConnector;
import com.allegory.users.model.NumberCheck;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserController {

	private final AmazonCognitoConnector amazonCognitoConnector;

	@Autowired
	public UserController(AmazonCognitoConnector amazonCognitoConnector) {
		this.amazonCognitoConnector = amazonCognitoConnector;
	}

	@Bean
	public Function<String, NumberStatus> numberCheck() {
		return amazonCognitoConnector::checkNumber;
	}

}
