package es.uji.vj1229.fortuji

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.uji.vj1229.fortuji.databinding.ActivityMainBinding
import es.uji.vj1229.fortuji.newsActivity.NewsActivity
import es.uji.vj1229.fortuji.searchActivity.SearchActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.newsButton.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent);
        }
        binding.cosmeticsButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent);
        }
    }

}
