package es.uji.vj1229.fortuji.network

import es.uji.vj1229.fortuji.searchActivity.CosmeticSearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FortniteAPI {
    @Headers("Accept: application/json")
    @GET("news/br")

    suspend fun getNews(): NewsResponse

    @Headers("Accept: application/json")
    @GET("cosmetics/br/search/all")
    suspend fun searchCosmeticsByName(
        @Query("name") name: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): CosmeticSearchResponse

    @Headers("Accept: application/json")
    @GET("cosmetics/br/search/all")
    suspend fun searchCosmeticsByDescription(
        @Query("description") description: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): CosmeticSearchResponse

    @Headers("Accept: application/json")
    @GET("cosmetics/br/search/all")
    suspend fun searchCosmeticsByID(
        @Query("id") id: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): CosmeticSearchResponse
}
