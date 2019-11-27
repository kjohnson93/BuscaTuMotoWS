package com.buscatumoto.mongodb

import org.springframework.core.NestedRuntimeException

class UserNotFoundException(val userId: String?) : NestedRuntimeException(
		String.format("User with  Id '%s' not founded", userId)
){
	
	
	
	
	
}