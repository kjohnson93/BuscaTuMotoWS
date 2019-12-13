package com.buscatumoto.services

import com.buscatumoto.util.BasicCrud
import com.buscatumoto.models.Moto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import java.util.Optional
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException

class MotoService(val branDAO: BrandDAO, val motoDAO: MotoDAO): BasicCrud<String, Moto> {
	
		override fun getAll(pageable: Pageable): Page<Moto> {
		return motoDAO.findAll(pageable)
	}

	override fun getById(id: String): Optional<Moto> {
		return motoDAO.findById(id)
		}

	override fun insert(obj: Moto): Moto {
		return motoDAO.insert(obj)
	}

	override fun update(obj: Moto): Moto {
//		return motoDAO.
		//upsert
//		motoDAO.findById(obj.id)?.let {
//			
//			
//			
//		} ?: run {
//			throw object: Exception("Trying to update a moto that does not exist.") {}
//		}
//		motoDAO.findById(obj.id).let {
//			
//			it.ifPresent {
//				return it
//			}
//			
//			
//		} ?: run {
//			throw object: Exception("Trying to update a moto that does not exist.") {}
//		}
//
		 if (motoDAO.existsById(obj.id)) {
			
			//re insert brand from db to avoid inconsistency
			return motoDAO.save(obj.apply {this.brand = branDAO.findById(obj.brand.id).get()})
			
		} else {
			throw object: Exception("Trying to update a moto that does not exist.") {}

		}
		
	}

	override fun deleteById(id: String): Optional<Moto> {
//		motoDAO.findById(id)?.let {
//			
//		} ?: run {
//			return 
//		}
			return motoDAO.findById(id).apply {
			this.ifPresent() {
				motoDAO.delete(it)
			}
		}
	}
}