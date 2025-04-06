package es.uji.vj1229.fortuji.searchActivity

import es.uji.vj1229.fortuji.common.Cosmetic

interface SearchView {
    fun showError(message: String)
    fun showCosmetics(cosmetics: ArrayList<Cosmetic>)
    fun showCurrentCosmeticDetails()
    fun startImagesActivity(name: String, images: ArrayList<String>)
    fun startVideo(video:String)
}
