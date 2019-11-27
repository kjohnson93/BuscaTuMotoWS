package com.buscatumoto.mongodb

/*
 Interface that defines basic CRUD operations.
*/
interface UserRepository {
	
	fun findAll(): List<User>
	fun saveUser(user:User): User?
	fun updateUser(user: User)
	fun deleteUser(userId: String)
	fun findOne(userId: String?): User?
}