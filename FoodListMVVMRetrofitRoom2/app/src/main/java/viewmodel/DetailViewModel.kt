package viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import model.Food
import services.FoodDatabase

class DetailViewModel(application: Application):BaseViewModel(application) {

    val foodLiveData= MutableLiveData<Food>()


    fun getRoomData(uuid: Int) {
        launch {

            val dao= FoodDatabase(getApplication()).foodDAO()
            val hi=dao.getFood(uuid)
            foodLiveData.value= hi
        }
    }
}