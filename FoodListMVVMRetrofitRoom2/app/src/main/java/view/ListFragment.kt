package view

import adapter.RecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodlistmvvmretrofitroom.R
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.recycler_adapter.*
import viewmodel.ListViewModel

class ListFragment : Fragment() {

    private val recyclerAdapter= RecyclerAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel=ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refreshData()


        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter=recyclerAdapter


        swipeRefresh.setOnRefreshListener {
            progressBar.visibility=View.VISIBLE
            recyclerView.visibility=View.GONE
            errorTextView.visibility=View.GONE
            viewModel.refreshFromInternet()
            swipeRefresh.isRefreshing= false
        }


        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner){
            recyclerView.visibility=View.VISIBLE
            recyclerAdapter.updateToFoodsList(it)
        }

        viewModel.foodsError.observe(viewLifecycleOwner){
            if (it){
                recyclerView.visibility=View.GONE
                errorTextView.visibility=View.VISIBLE
            }else{
                errorTextView.visibility=View.GONE
            }
        }
        viewModel.foodsLoading.observe(viewLifecycleOwner){

            if (it){
                progressBar.visibility=View.VISIBLE
                recyclerView.visibility=View.GONE
                errorTextView.visibility=View.GONE
            }else{
                progressBar.visibility=View.GONE
            }
        }
    }

}