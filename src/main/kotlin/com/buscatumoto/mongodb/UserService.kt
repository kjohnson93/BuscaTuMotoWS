package com.buscatumoto.mongodb

interface UserService {
	
	fun findAll(): List<User>
	fun findByUserId(userId: String): User?
	fun saveUser(user:User): User?
	fun updateUser(user: User)
	fun deleteUser(userId: String)
}