package com.buscatumoto.model

import com.buscatumoto.models.Moto


data class MotoResponse(var respuesta: String? = "", var listMotos : List<Moto>)

data class MotoFieldResponse(var respuesta: String? = "",  var brandList: List<String> = emptyList(),
							 var bikeTypesList: List<String> = emptyList(), var yearList: List<Int> = emptyList(),
							 var priceMinList: List<Int> = emptyList(), var priceMaxList: List<Int> = emptyList(),
							 var powerMinList: List<Float> = emptyList(), var powerMaxList: List<Float> = emptyList(),
							 var cilMinList: List<Float> = emptyList(), var cilMaxList: List<Float> = emptyList(),
							 var weightMinList: List<Float> = emptyList(), var weightMaxList: List<Float> = emptyList(),
							 var licenses: List<String> = emptyList() )
