package view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.foodlistmvvmretrofitroom.R
import kotlinx.android.synthetic.main.fragment_detail.*
import util.makePlaceHolder
import util.pickPicture
import viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private var foodId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            foodId=  DetailFragmentArgs.fromBundle(it).foodIdArgument
        }

        detailViewModel= ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.getRoomData(foodId)

        observeDetail()

    }

    private fun observeDetail(){

        detailViewModel.foodLiveData.observe(viewLifecycleOwner){ let->

            let?.let {
                FoodName.text=it.modelFoodName
                FoodCalorie.text=it.modelFoodCalorie
                FoodCarbohydrate.text=it.modelFoodCarbohydrate
                FoodProtein.text=it.modelFoodProtein
                FoodOil.text=it.modelFoodOil
                context?.let {context->
                    DetailImage.pickPicture(it.modelFoodImage, makePlaceHolder(context))
                }

            }

        }
    }

}