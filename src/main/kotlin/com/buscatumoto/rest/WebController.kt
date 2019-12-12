package com.buscatumoto.rest

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.buscatumoto.rest.model.CustomerModel
import javax.annotation.PostConstruct
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("api/customer")
class WebController {
	
	val customerStores = mutableMapOf<Long, CustomerModel>()
	
	@PostConstruct
	fun initial() {
		customerStores.put(1, CustomerModel(1, "Jack", "Smith", 20))
		customerStores.put(2, CustomerModel(2, "Koop", "Kooper", 26))
	}
	
	@GetMapping("/")
	fun getAll(): MutableMap<Long, CustomerModel> {
		println("########### GET All Customers: $customerStores")
		
		return customerStores
	}
	
	fun getCustomerScuffed(id: Long): CustomerModel{
		val customer = customerStores.getValue(id)
		
		println("########### GET a Customers with $customer")
		return customer
	}
	
	//test git
	@GetMapping("/get")
	fun getCustomer(@RequestParam("id") id: Long): CustomerModel? {
		return customerStores.get(id)
	}
	
	@PostMapping("/post")
	fun postCustomer(@RequestBody customerModel: CustomerModel): String {
		//do post
		customerStores.put(customerModel.id, customerModel)
		
		//log on console
		println("######### POST: + $customerModel")
		
		return "Post Successfully"
	}
	
	@PutMapping("/put/{id}")
	fun putCustomer(@PathVariable id: Long, @RequestBody customer: CustomerModel): String {
		
		//reset customer.Id
		customer.id = id
		
		if (customer.id != null) {
			customerStores.replace(id, customer)
		} else {
			customerStores.put(id, customer)
		}
		
		println("########## PUT:" + customer)
		
		return "Put Successfully"
	}
	
	@DeleteMapping("/delete/{id}")
	fun deleteMethod(@PathVariable id: Long): String {
		
	val cust: CustomerModel? = customerStores.remove(id)
		
	if (cust != null) {
		println("Deleted an existing customer with id: $id")
	} else {
		println("Could not delete because customer with id $id does not exist.")
	}
		
		return "Deleted"
		
	}
	
	
}