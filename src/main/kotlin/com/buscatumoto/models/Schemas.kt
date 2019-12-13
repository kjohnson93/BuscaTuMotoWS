package com.buscatumoto.models

data class Brand(val id: String,val name: String)

data class Moto(val id: String, var brand: Brand, val model: String, val imgThumbUrl: String,
					val modelHighlights: String, val imgBannerUrl: String,
					val modelDetailHighlights: List<String>, val priceTitle: String, val priceDesc: String,
					val mainDesc: String, val licenses: List<String>, val licensesTitle: String,
					val specsTable: List<List<String>>, val relatedItems: List<String>,
					val relatedItemsUrl: List<String>) {
}