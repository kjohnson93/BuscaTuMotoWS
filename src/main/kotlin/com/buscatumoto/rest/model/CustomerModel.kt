package com.buscatumoto.rest.model

data class CustomerModel(
	var id: Long = -1,
	var firstName: String = "",
	var lastName: String = "",
	var age: Int = -1
)