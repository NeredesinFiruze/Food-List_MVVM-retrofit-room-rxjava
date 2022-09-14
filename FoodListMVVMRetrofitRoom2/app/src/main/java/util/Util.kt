package util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodlistmvvmretrofitroom.R



fun ImageView.pickPicture(url: String?, cph: CircularProgressDrawable){
    val option=RequestOptions().placeholder(cph).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(option).load(url).into(this)
}

fun makePlaceHolder(context: Context): CircularProgressDrawable{

    return CircularProgressDrawable(context).apply {

        strokeWidth=8f
        centerRadius=40f
        start()
    }

}