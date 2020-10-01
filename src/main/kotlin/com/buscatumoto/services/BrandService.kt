package com.buscatumoto.services

import com.buscatumoto.util.BasicCrud
import com.buscatumoto.models.Brand
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import java.util.Optional
import org.springframework.stereotype.Service

@Service//declare this class as a Service "Component specialization"
/*injects DAO objects by constructor & implements BasicCrud interface*/
class BrandService(val brandDAO:BrandDAO, val motoDAO: MotoDAO) : BasicCrud<String, Brand>{
	override fun getAll(pageable: Pageable): Page<Brand> {
		return brandDAO.findAll(pageable)
	}

	override fun getById(id: String): Optional<Brand> {
		return brandDAO.findById(id)
	}

	override fun insert(obj: Brand): Brand {
		return brandDAO.insert(obj)
	}

//	override fun update(obj: Brand): Brand {
//		//mongodb only update instead of upsert:
//		brandDAO.findById(obj.id)?.let {
//			//update
//			//first update all his bikes
//			motoDAO.saveAll(motoDAO.findByBrand(obj.id!!).map {
//				it.also {
//					it.brand = obj.id
//				}
//			})
//			return brandDAO.save(obj)
//		} ?: run {
//			throw object: Exception ("Trying to update an object that does not exist.") {}
//		}
//	
//	}

	override fun deleteById(id: String): Optional<Brand> {
		return brandDAO.findById(id).apply {
			this.ifPresent {
				brandDAO.delete(it)
			}
		}
	}
}