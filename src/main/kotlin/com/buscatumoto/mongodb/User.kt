package com.buscatumoto.mongodb

import java.io.Serializable
import org.springframework.data.mongodb.core.mapping.Document

//Specificies this POJO will be stored in collection "users"
@Document(collection = "users")

class User: Serializable {
	
	companion object {
		const val SERIAL_VERSION_UID = 7788619177798333712L
	}
	
	var userId: String? = null
	get(){
		return field
	}
	set(value) {
		field = value
	}
	
	var name: String? = null
	get(){
		return field
	}
	set(value) {
		field = value
	}
	
}