package es.uji.vj1229.fortuji.searchActivity

class CosmeticSearchResponse (val data: List<CosmeticResponse>) {
    class CosmeticResponse(
        val id: String,
        val name: String,
        val description: String,
        val type: TypeResponse,
        val rarity: RarityResponse,
        val images: ImagesResponse,
        val showcaseVideo: String?,
        val added: String,
    )

    class TypeResponse(
        val value: String,
        val displayValue: String
    )

    class RarityResponse(
        val value: String,
        val displayValue: String
    )

    class ImagesResponse(
        val bean: ImageSizeResponse?,
        val featured: String?,
        val icon: String?,
        val lego: ImageSizeResponse?,
        val other: OtherResponse?,
        val smallIcon: String?,
    )

    class ImageSizeResponse(
        val large : String?,
        val small : String?,
        val wide : String?
    )

    class OtherResponse(
        val background: String?,
        val coverart: String?,
        val decal: String?,
    )
}
