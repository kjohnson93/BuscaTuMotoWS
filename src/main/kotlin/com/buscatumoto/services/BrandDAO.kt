package com.buscatumoto.services

import org.springframework.data.mongodb.repository.MongoRepository
import com.buscatumoto.models.Brand
import com.buscatumoto.models.Moto

interface BrandDAO: MongoRepository<Brand, String>

