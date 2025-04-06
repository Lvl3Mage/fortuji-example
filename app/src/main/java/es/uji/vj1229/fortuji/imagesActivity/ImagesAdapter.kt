package es.uji.vj1229.fortuji.imagesActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.uji.vj1229.fortuji.databinding.ImageRowBinding

class ImagesAdapter(val images: ArrayList<Pair<String,String>>, val context: Context) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>(){
    class ViewHolder(val binding: ImageRowBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageRowBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = images.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding.imageRowTitle.text = images[position].first
        Glide.with(context)
            .load(images[position].second)
            .fitCenter()
            .into(binding.imagesRow)
    }

}