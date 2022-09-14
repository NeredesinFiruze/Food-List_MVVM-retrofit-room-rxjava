package viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import kotlinx.coroutines.launch
import model.Food
import services.FoodApiServices
import services.FoodDatabase
import util.SharedPreferences

class ListViewModel (application: Application):BaseViewModel(application) {

    val foods = MutableLiveData<List<Food>>()
    val foodsLoading= MutableLiveData<Boolean>()
    val foodsError= MutableLiveData<Boolean>()

    private val disposable= CompositeDisposable()
    private val foodApiServices= FoodApiServices()
    private val getSharedPreferences= SharedPreferences(getApplication())

    private val refreshTime= 10*60*1000*1000*1000L

    fun refreshData(){
        val whenItWasRecord= getSharedPreferences.takeTime()
        if (whenItWasRecord != null && whenItWasRecord != 0L && System.nanoTime() - whenItWasRecord < refreshTime){
            getDataFromDataBase()
        }else{
            getDataFromInternet()
        }

    }
    fun refreshFromInternet(){
       getDataFromInternet()
    }


    private fun getDataFromInternet(){
        foodsLoading.value=true

        disposable.add(foodApiServices.getData().subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Food>>(),Disposable{
                override fun onSuccess(t: List<Food>) {
                    hostSqlite(t)

                }

                override fun onError(e: Throwable) {
                    foodsError.value=true
                    foodsLoading.value=false
                    e.printStackTrace()
                }

            })

        )

    }

    private fun getDataFromDataBase(){
        foodsLoading.value= true

        launch {
            val foodList= FoodDatabase(getApplication()).foodDAO().getAllFood()
            showFoods(foodList)
        }
    }

    private fun showFoods(t: List<Food>){
        foods.value=t
        foodsLoading.value=false
        foodsError.value=false
    }

    private fun hostSqlite(t: List<Food>){
        launch {
            val dao= FoodDatabase(getApplication()).foodDAO()
            dao.deleteAllFood()
            val uuidList=dao.insertALl(*t.toTypedArray())
            var i =0
            while (i<t.size){
                t[i].uuid= uuidList[i].toInt()
                i += 1
            }

            showFoods(t)


        }
        getSharedPreferences.recordTime(System.nanoTime())
    }
}
