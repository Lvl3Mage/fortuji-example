package es.uji.vj1229.fortuji.newsActivity

import es.uji.vj1229.fortuji.common.News

interface NewsView {
    fun showError(err:String);
    fun showImage(imageUrl: String?);
    fun showNews(news: ArrayList<News>)
    fun showCurrentNewsDetails()


}