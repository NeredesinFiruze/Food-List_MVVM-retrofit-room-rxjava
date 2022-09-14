package services

import io.reactivex.rxjava3.core.Single
import model.Food
import retrofit2.http.GET

interface FoodAPI {

    @GET("/atilsamancioglu/BTK20-JSONVeriSeti/9b55086ad4096dbee7bc2b506d4787fbb805b0ca/besinler.json")

    fun getFood(): Single<List<Food>>

}