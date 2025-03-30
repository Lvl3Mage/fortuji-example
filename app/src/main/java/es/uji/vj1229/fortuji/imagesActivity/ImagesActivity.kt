package es.uji.vj1229.fortuji.imagesActivity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.uji.vj1229.fortuji.databinding.ActivityImagesBinding

class ImagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImagesBinding
    //TODO: these are not set by that weird string thing when the activity is starter
    //I could also just be wrong idk
    var name : String? = null
    var images : ArrayList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imagesRecycler.layoutManager = LinearLayoutManager(this);
        binding.imagesRecycler.adapter = ImagesAdapter(images, this)
    }

}
