package es.uji.vj1229.fortuji.searchActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.network.FortniteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    var view: SearchView? = null
        set(value) {
            field = value
            showCosmetics(cosmetics)
        }

    enum class SearchType(val label: String) {
        NAME("Name"), DESCRIPTION("Description"), ID("ID")
    }

    var imageURL: String? = null
    var cosmetics = ArrayList<Cosmetic>()
    var selectedCosmetic: Cosmetic? = null

    fun onSearchRequested(type: SearchType, query: String) {
        viewModelScope.launch(Dispatchers.Main) {
            FortniteRepository.search(type, query)
                .onSuccess {
                    cosmetics = it;
                    showCosmetics(cosmetics)
                }
                .onFailure {
                    showErrorMessage("\"$query\" not found")
                }
        }
    }

    fun showCosmetics(cosmetics: ArrayList<Cosmetic>) {
        view?.showCosmetics(cosmetics)
    }

    fun showErrorMessage(message: String) {
        view?.showError(message)
    }

    fun onCosmeticSelected(cosmetic: Cosmetic) {
        selectedCosmetic = cosmetic
        view?.showCurrentCosmeticDetails()
    }

    fun onShowImagesRequested() = selectedCosmetic?.let {
        val images = ArrayList<String>()
        for (entry in it.images.entries) {
            images.add(entry.key)
            images.add(entry.value)
        }
        view?.startImagesActivity(it.name, images)
    }

    fun onStartVideoRequested() {
        selectedCosmetic?.let {
            it.video?.let {
                video -> view?.startVideo(video)
            }
        }
    }


}
