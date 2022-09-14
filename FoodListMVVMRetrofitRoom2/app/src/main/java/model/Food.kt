package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
class Food (
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val modelFoodName : String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val modelFoodCalorie : String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val modelFoodCarbohydrate : String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val modelFoodProtein : String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val modelFoodOil : String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val modelFoodImage : String?){

    @PrimaryKey(autoGenerate = true)
    var uuid : Int =0
}