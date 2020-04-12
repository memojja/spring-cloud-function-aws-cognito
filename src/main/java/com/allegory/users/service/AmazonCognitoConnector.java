package com.allegory.users.service;

import com.allegory.users.constant.CognitoConfig;
import com.allegory.users.constant.CognitoConstants;
import com.allegory.users.model.NumberCheck;
import com.allegory.users.model.NumberStatus;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Component
public class AmazonCognitoConnector {

	private final AWSCognitoIdentityProvider identityProvider;

	@Autowired
	public AmazonCognitoConnector(AWSCognitoIdentityProvider identityProvider) {
		this.identityProvider = identityProvider;
	}


	public NumberStatus checkNumber(String number) {
		ListUsersRequest listUsersRequest = createListUsersRequest();
		ListUsersResult result = null;
		do {
			try {
				result = identityProvider.listUsers(listUsersRequest);
				if(NumberCheck.AVAILABLE.equals(searchNumberFromResultByNumber(result,number)))
					return new NumberStatus(NumberCheck.AVAILABLE);
			} catch (TooManyRequestsException e) {	/** cognito hard rate limit for "list users": 5 per second. */
				try {
					Thread.sleep(200);
				} catch (InterruptedException ignored) {
				}
			}finally {
				listUsersRequest.setPaginationToken(result.getPaginationToken());
			}
		} while (result.getPaginationToken() != null);

		return new NumberStatus(NumberCheck.NOT_AVAILABLE);
	}

	private NumberCheck searchNumberFromResultByNumber(ListUsersResult result,String number){
		Optional<String> userTypes = result
				.getUsers()
				.parallelStream()
				.flatMap(user -> user.getAttributes()
						.stream()
						.filter(attributeType -> attributeType.getName().equals(CognitoConstants.PHONE_NUMBER)).map(AttributeType::getValue)
				)
				.filter(telNumber -> telNumber.contains(number))
				.findAny();

		if(userTypes.isPresent()){
			return NumberCheck.AVAILABLE;
		}
		return NumberCheck.NOT_AVAILABLE;
	}

	private ListUsersRequest createListUsersRequest(){
		ListUsersRequest listUsersRequest = new ListUsersRequest();
		listUsersRequest.withUserPoolId(CognitoConfig.AWS_USER_POOL_ID);
		listUsersRequest.setLimit(CognitoConfig.COGNITO_LIMIT_PER_REQ_USER);
		return listUsersRequest;
	}

}
