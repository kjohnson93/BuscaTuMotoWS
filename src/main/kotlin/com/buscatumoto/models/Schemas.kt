package com.buscatumoto.models

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

data class Brand(var id: String?,var name: String?)

@Document(collection = "motos_copy")
data class Moto(@Id var id: String? = null, var bikeType: String = "", var brand: String = "",
					var price: String = "", var model: String = "", var imgThumbUrl: String = "",
					var modelHighlights: String = "", var imgBannerUrl: String = "",
					var modelDetailHighlights: List<String> = emptyList(), var priceTitle: String = "",
					var priceDesc: String = "",var mainDesc: String = "",
					var licenses: List<String> = emptyList(), var licensesTitle: String = "",
					var specsTable: List<List<String>> = emptyList(),
					var relatedItems: List<String> = emptyList(),
					var relatedItemsUrl: List<String> = emptyList()) {
}
