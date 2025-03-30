package es.uji.vj1229.fortuji.network

class NewsResponse(val data: DataResponse) {
    class DataResponse(
        val image: String,
        val motds: List<MotdResponse>
    )

    class MotdResponse(
        val title: String,
        val tabTitle: String,
        val tileImage: String,
        val body: String
    )

}
