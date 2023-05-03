package com.alina.pixa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.alina.pixa.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding

    private var adapter = PixaAdapter(arrayListOf())
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()

    }

    private fun initClickers() {
        with(binding) {
            rvRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!rvRecycler.canScrollVertically(1)) {
                        binding.requestImage(page++)
                    }
                }

            })

            btnNextPage.setOnClickListener {

                requestImage(++page)
            }
            btnSearch.setOnClickListener {
                requestImage(page = 1)
            }
        }
    }


    private fun ActivityMainBinding.requestImage(page: Int) {
        RetrofitService().api.getImages(search = binding.edSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    response.body()?.let {
                        if (response.isSuccessful) {
                            adapter.addData(response.body()?.hits!!)
                            rvRecycler.adapter = adapter
                        }
                    }

                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure:${t.message}")
                }
            })

    }


}