package es.uji.vj1229.fortuji.newsActivity

import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import es.uji.vj1229.fortuji.R
import es.uji.vj1229.fortuji.common.News
import es.uji.vj1229.fortuji.databinding.ActivityNewsBinding


class NewsActivity : AppCompatActivity(), NewsView {

    private val viewModel: NewsViewModel by viewModels<NewsViewModel>()
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this);
    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this
    }

    override fun onPause() {
        super.onPause()
        viewModel.view = null
    }

    override fun showError(err: String) {
        val ctw = ContextThemeWrapper(this, R.style.CustomSnackbarTheme)
        Snackbar.make(ctw, binding.root, err, Snackbar.LENGTH_LONG).show()
    }

    override fun showImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(binding.newsImageView)
    }

    override fun showNews(news: ArrayList<News>) {
        binding.newsRecyclerView.adapter = NewsAdapter(news) {
            viewModel.onNewsSelected(it)
        }
    }

    override fun showCurrentNewsDetails() {
        NewsDialog().show(supportFragmentManager, "NewsDialog")
    }
}
