package com.buscatumoto.mongodb

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid
import org.springframework.web.bind.annotation.PostMapping


@RestController
@RequestMapping("users")
class UsersController(val usersService: UserService) {
	
		companion object {
		val log = LogFactory.getLog(UserServiceImpl::class.java)
	}

	var user: User? = null	
	
	//Workaround arrayOf to avoid error Assigning single elements to varargs in named form is deprecated
    @GetMapping(value= arrayOf("/{userId}"))
	fun userById(@PathVariable userId: String): ResponseEntity<User>?  {
		
		log.info("Get userById")
		var result : ResponseEntity<User>? = null
		
		try {
			user = usersService.findByUserId(userId)
			
			user?.let {
				result = ResponseEntity.ok(it)

			} 

		} catch(e: UserNotFoundException) {
			user = null
		}
		
		
		return result
	}
	
	@GetMapping
	fun userById(): ResponseEntity<List<User>> {
		log.info("Get allUsers")
		
		return ResponseEntity.ok(usersService.findAll())
	}
	
	@DeleteMapping(value=arrayOf("/{userId}"))
	fun deleteUser(@PathVariable userId: String): ResponseEntity<Void> {
	log.info("Delete user $userId")
		
		usersService.deleteUser(userId)
	
	return ResponseEntity.noContent().build()
	}
	
	@PostMapping
	fun saveUser(@RequestBody @Valid user: User): ResponseEntity<User>? {
		log.info("Save new user")
		
		var result: ResponseEntity<User>? = null
		
		var userSaved = usersService.saveUser(user)
		
		userSaved?.let {
			result = ResponseEntity.ok(it)
		}

		return result 
	}
	
	
	
	
	
}