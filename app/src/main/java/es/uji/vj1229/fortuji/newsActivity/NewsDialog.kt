package es.uji.vj1229.fortuji.newsActivity

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import es.uji.vj1229.fortuji.R
import es.uji.vj1229.fortuji.databinding.NewsDialogBinding

class NewsDialog : DialogFragment() {
    private val viewModel: NewsViewModel by activityViewModels()
    private lateinit var binding: NewsDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = NewsDialogBinding.inflate(layoutInflater, null, false)
        with(binding) {
            activity?.let {
                viewModel.selectedNews?.apply {
                    Glide.with(it)
                        .load(this.image)
                        .fitCenter()
                        .into(newsDialogImageView)
                    newsTitleTextView.text = this.title
                    newsBodyTextView.text = this.body
                }
            }
        }
        val dialog = AlertDialog.Builder(requireContext()).run {
            setView(binding.root)
            setPositiveButton(android.R.string.ok, null)
            create()
        }
        dialog.window?.setBackgroundDrawableResource(R.color.background)
        return dialog
    }

}