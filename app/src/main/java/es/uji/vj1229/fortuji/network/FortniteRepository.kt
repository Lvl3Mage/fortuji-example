package es.uji.vj1229.fortuji.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.uji.vj1229.fortuji.searchActivity.SearchViewModel
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.common.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FortniteRepository {
    companion object{

        private val api: FortniteAPI
        init {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            api = Retrofit.Builder()
                .baseUrl("https://fortnite-api.com/v2/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(FortniteAPI::class.java)
        }
        suspend fun getNews() = try {
            withContext(Dispatchers.IO) {
                val newsResponse = api.getNews()
                val news = ArrayList<News>()
                for (motd in newsResponse.data.motds) {
                    news.add(News(motd.title, motd.tabTitle, motd.tileImage, motd.body))
                }
                Result.success(Pair(newsResponse.data.image, news))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

        suspend fun search(type: SearchViewModel.SearchType, query: String) = try {
            withContext(Dispatchers.IO) {
                val searchResponse =
                    when (type) {
                        SearchViewModel.SearchType.NAME -> api.searchCosmeticsByName(query)
                        SearchViewModel.SearchType.DESCRIPTION -> api.searchCosmeticsByDescription( query )
                        SearchViewModel.SearchType.ID -> api.searchCosmeticsByID(query)
                    }
                val search = ArrayList<Cosmetic>()
                for (cosmetic in searchResponse.data) {

                    fun <K, V> hashMapOfNotNull(vararg pairs: Pair<K, V?>): HashMap<K, V> =
                        HashMap(pairs.mapNotNull {
                            it.second?.let { value ->
                                it.first to value
                            }
                        }.toMap())

                    search.add(
                        Cosmetic(
                            cosmetic.id,
                            cosmetic.name,
                            cosmetic.description,
                            cosmetic.type.displayValue,
                            cosmetic.rarity.displayValue,
                            hashMapOfNotNull(
                                "icon" to cosmetic.images.icon,
                                "featured" to cosmetic.images.featured,
                                "smallIcon" to cosmetic.images.smallIcon,
                                "bean small" to cosmetic.images.bean?.small,
                                "bean large" to cosmetic.images.bean?.large,
                                "bean wide" to cosmetic.images.bean?.wide,
                                "lego small" to cosmetic.images.lego?.small,
                                "lego large" to cosmetic.images.lego?.large,
                                "lego wide" to cosmetic.images.lego?.wide
                            ),
                            cosmetic.showcaseVideo,
                            cosmetic.added
                        )
                    )
                }
                Result.success(search)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        fun <K, V> mapOfNotNull(vararg pairs: Pair<K, V?>): Map<K, V> =
            pairs.mapNotNull { it.second?.let { value -> it.first to value } }.toMap()

    }


}
