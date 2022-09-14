package services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import model.Food

@Dao
interface FoodDAO {

    @Insert
    suspend fun insertALl(vararg food: Food): List<Long>

    @Query("SELECT *FROM food")
    suspend fun getAllFood(): List<Food>

    @Query("SELECT * FROM food WHERE uuid= :foodId")
    suspend fun getFood(foodId : Int): Food

    @Query("DELETE  FROM food")
    suspend fun deleteAllFood()
}