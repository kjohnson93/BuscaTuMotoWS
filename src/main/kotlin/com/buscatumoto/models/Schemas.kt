package com.buscatumoto.models

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field

data class Brand(var _id: String?,var brand: String?)

@Document(collection = "motos")
data class Moto(@Id var id: String? = null, var bikeType: String = "", var brand: String = "",
					var model: String = "", var price: Int = 0,
					var power: Int = 0, var displacement: Float = 0f,
					var weight: Float = 0f, var year: Int = 0,
					var imgThumbUrl: String = "",
					var modelHighlights: String = "", var imgBannerUrl: String = "",
					var modelDetailHighlights: String? = null, var priceTitle: String = "",
					var priceDesc: String = "",var mainDesc: String = "",
					var licenses: List<String> = emptyList(),
					@Field("licenses_title") var licensesTitle: String = "",
					@Field("relatedItems") var relatedItems: List<String> = emptyList(),
					@Field("relatedItemsUrl") var relatedItemsUrl: List<String> = emptyList()) {
}

@Document(collection = "moto_fields")
data class MotoField(@Id var id: String? = null,
					 var priceMin: List<Int> = emptyList(), var priceMax: List<Int> = emptyList(),
					 var powerMin: List<Float> = emptyList(), var powerMax: List<Float> = emptyList(),
					 var cilMin: List<Float> = emptyList(), var cilMax: List<Float> = emptyList(),
					 var weightMin: List<Float> = emptyList(), var weightMax: List<Float> = emptyList(),
					 var licenses: List<String> = emptyList())

