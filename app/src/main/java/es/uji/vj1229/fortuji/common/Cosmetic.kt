package es.uji.vj1229.fortuji.common

data class Cosmetic (
    val id: String,
    val name: String,
    val description: String,
    val type: String,
    val rarity: String,
    val images: HashMap<String, String>,
    val video: String?,
    val added: String

)
