package com.harshul.storeapp.data.api

import com.harshul.storeapp.data.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface FakerAPI {

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

}