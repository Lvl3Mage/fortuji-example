package es.uji.vj1229.fortuji.searchActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.databinding.SearchRowBinding

class CosmeticsAdapter(val cosmetics: ArrayList<Cosmetic>, val onClickListener: (Cosmetic) -> Unit) : RecyclerView.Adapter<CosmeticsAdapter.ViewHolder>() {
    class ViewHolder(val binding: SearchRowBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchRowBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int { return cosmetics.count() }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        with(cosmetics[position]){
            binding.searchText.text = name
            binding.subTextSearch.text = description
        }
        binding.searchText.setOnClickListener{
            onClickListener(cosmetics[position])
        }
    }
}
