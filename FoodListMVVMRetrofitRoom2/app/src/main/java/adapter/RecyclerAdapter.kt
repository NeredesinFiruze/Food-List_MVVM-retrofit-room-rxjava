package adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlistmvvmretrofitroom.R
import kotlinx.android.synthetic.main.recycler_adapter.view.*
import model.Food
import util.makePlaceHolder
import util.pickPicture
import view.ListFragmentDirections

class RecyclerAdapter(private val FoodItem: ArrayList<Food>): RecyclerView.Adapter<RecyclerAdapter.FoodViewHolder>(){

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflate= LayoutInflater.from(parent.context)
        val i= inflate.inflate(R.layout.recycler_adapter,parent,false)
        return FoodViewHolder(i)
    }

    override fun getItemCount(): Int {
        return FoodItem.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.itemView.ListFoodName.text=FoodItem[position].modelFoodName
        holder.itemView.ListFoodCalorie.text= FoodItem[position].modelFoodCalorie
        holder.itemView.ImageViewRecycler.pickPicture(FoodItem[position].modelFoodImage,makePlaceHolder(holder.itemView.context))

        holder.itemView.setOnClickListener {

            val act= ListFragmentDirections.actionListFragmentToDetailFragment()
            act.foodIdArgument = FoodItem[position].uuid
            Navigation.findNavController(it).navigate(act)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateToFoodsList(list: List<Food>){
        FoodItem.clear()
        FoodItem.addAll(list)
        notifyDataSetChanged()
    }

}

