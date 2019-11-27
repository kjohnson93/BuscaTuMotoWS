package com.buscatumoto.mongodb

import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

@Repository
class UserRepositoryImpl(@Autowired var mongoOperations: MongoOperations): UserRepository {
	

	
	override fun findAll(): List<User> {

		var users = this.mongoOperations.find(Query(), User::class.java)
		
		return users
	
		}

	override fun saveUser(user: User): User? {
		
		this.mongoOperations.save(user)
		
		return findOne(user.userId)	
	}
	
	override fun findOne(userId: String?): User? {
		
		var result: User? = null
		
		val criteria = Criteria.where("userId").`is`(userId)
		val query = Query(criteria)
		
		result = mongoOperations.findOne(
                                query,
                                User::class.java
                            )
		
		return result		
	}

	override fun updateUser(user: User) {
			this.mongoOperations.save(user)
		}

	override fun deleteUser(userId: String) {
		
		val criteria = Criteria.where("userId").`is`(userId)
		val query = Query(criteria)
			this.mongoOperations.findAndRemove(query, User::class.java)
		}
}