package es.uji.vj1229.fortuji.searchActivity

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.databinding.ActivitySearchBinding
import es.uji.vj1229.fortuji.imagesActivity.ImagesActivity


class SearchActivity : AppCompatActivity(), SearchView {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel : SearchViewModel by viewModels<SearchViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.view = this

        val options = arrayOf(
            SearchViewModel.SearchType.NAME,
            SearchViewModel.SearchType.DESCRIPTION,
            SearchViewModel.SearchType.ID
        )
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, options)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.searchTypeSpinner.adapter = adapter

        binding.searchCosmeticsRecycler.layoutManager = LinearLayoutManager(this)
        binding.searchButton.setOnClickListener {
            viewModel.onSearchRequested(
                binding.searchTypeSpinner.selectedItem as SearchViewModel.SearchType,
                binding.searchContentEditText.text.toString()
            )
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.view = this
    }
    override fun onPause() {
        super.onPause()
        viewModel.view = null
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showCosmetics(cosmetics: ArrayList<Cosmetic>) {
        binding.searchCosmeticsRecycler.adapter = CosmeticsAdapter(cosmetics) {
            viewModel.onCosmeticSelected(it)
        }
    }
    override fun showCurrentCosmeticDetails(){
        CosmeticDialog().show(supportFragmentManager, "cosmeticDialog")
    }

    override fun startImagesActivity(name: String, images: ArrayList<String>) {
        val intent = Intent(this, ImagesActivity::class.java)
        intent.putExtra("name", name)
        intent.putStringArrayListExtra("images", images)
        startActivity(intent)
    }
}
