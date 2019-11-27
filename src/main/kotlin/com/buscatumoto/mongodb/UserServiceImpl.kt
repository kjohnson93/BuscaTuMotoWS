package com.buscatumoto.mongodb

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired

@Service("userService")
@Transactional
class UserServiceImpl(val userRepository: UserRepository): UserService{
	
	companion object {
		val LOG = LogFactory.getLog(UserServiceImpl::class.java)
	}
	
	
	override fun findAll(): List<User> {

		var result = userRepository.findAll()
		
		return result
		}

	
	override fun findByUserId(userId: String): User? {

		var user = userRepository.findOne(userId)
		
		user?.let {
			LOG.debug(String.format("Read userId '{}", userId))
			
			return user
		} ?: run {
			//throw new UserNotFoundException instead of null
			val userNotFoundExcept = UserNotFoundException(userId)
			
			throw userNotFoundExcept
		}
		
		}

	override fun saveUser(user: User): User? {
		return userRepository.saveUser(user)
		}

	override fun updateUser(user: User) {
		userRepository.updateUser(user)
	}

	override fun deleteUser(userId: String) {
		userRepository.deleteUser(userId)
		}
}