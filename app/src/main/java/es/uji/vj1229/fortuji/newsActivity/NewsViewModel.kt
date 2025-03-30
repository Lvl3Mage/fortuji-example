package es.uji.vj1229.fortuji.newsActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uji.vj1229.fortuji.network.FortniteRepository
import es.uji.vj1229.fortuji.common.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val news = ArrayList<News>()
    private var imageURL: String? = null
    var selectedNews: News? = null
        private set
    var view: NewsView? = null
        set(value) {
            field = value
            showNews()
        }
    init {
        loadNews()
    }
    private fun loadNews() {
        viewModelScope.launch(Dispatchers.Main) {
            FortniteRepository.getNews()
                .onSuccess {
                    imageURL = it.first
                    news.clear()
                    news.addAll(it.second)
                    showNews()
                }
                .onFailure {
                    view?.showError("Error loading news")
                }
        }
    }
    private fun showNews(){
        view?.apply{
            showImage(imageURL)
            showNews(news)
        }
    }
    fun onNewsSelected(news: News) {
        selectedNews = news
        view?.showCurrentNewsDetails()
    }

}