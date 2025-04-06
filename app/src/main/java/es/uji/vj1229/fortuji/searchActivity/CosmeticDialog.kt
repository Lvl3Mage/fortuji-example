package es.uji.vj1229.fortuji.searchActivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import es.uji.vj1229.fortuji.R
import es.uji.vj1229.fortuji.databinding.ActivityCosmeticsDialogBinding

class CosmeticDialog : DialogFragment(){
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: ActivityCosmeticsDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ActivityCosmeticsDialogBinding.inflate(layoutInflater, null, false)
        with(binding) {
            activity?.let {
                viewModel.selectedCosmetic?.apply {
                    val icon: String? = this.images.get("icon")
                    Glide.with(it)
                        .load(icon)
                        .fitCenter()
                        .into(imageImageView)
                    idTextView.text = this.id
                    binding.nameTextView.text = this.name
                    typeTextView.text = this.type
                    rarityTextView.text = this.rarity
                    addedTextView.text = this.added
                    descriptionTextView.text = this.description

                }
            }
        }
        binding.imagesButton.setOnClickListener {
            viewModel.selectedCosmetic?.let {
                viewModel.onShowImagesRequested()
            }
        }
        viewModel.selectedCosmetic?.let {
            if (it.video == null){
                binding.videoButton.isEnabled = false
            }
            else{
                binding.videoButton.setOnClickListener{
                    viewModel.onStartVideoRequested()
                }

            }
        }
        val dialog =AlertDialog.Builder(requireContext()).run {
            setView(binding.root)
            setPositiveButton(android.R.string.ok, null)
            create()
        }
        dialog.window?.setBackgroundDrawableResource(R.color.background)
        return dialog;
    }
}
