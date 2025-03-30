package es.uji.vj1229.fortuji.newsActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.vj1229.fortuji.common.News
import es.uji.vj1229.fortuji.databinding.NewsRowBinding

class NewsAdapter(val news: ArrayList<News>,private val onClickListener: (News) -> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    class ViewHolder(val binding: NewsRowBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsRowBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = news.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        with(news[position]){
            binding.newsText.text = title
        }
        binding.newsText.setOnClickListener{
            onClickListener(news[position])
        }
    }

}
