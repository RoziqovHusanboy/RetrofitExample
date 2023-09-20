package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoAdapter = TodoAdapter()
        userAdapter = UserAdapter()

        val retrofit =
            Retrofit.Builder().baseUrl("https://reqres.in").addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        val api = retrofit.create(ApiInterface::class.java)

//        lifecycleScope.launch {
//           val response =  api.getUserById(2)
//            if (response.isSuccessful){
//                val userListResponse = response.body()
//            }else{
//
//            }
//
//        }

//        lifecycleScope.launch {
//         val response =  api.createUser(UserCreate("","","Modile Developer","Husanboy"))
//            if (response.isSuccessful) {
//                Log.d("TAG", "onCreate: ${response.body()}")
//
//            }else{
//                Log.d("TAG", "onCreate: Sorry something with creating ")
//            }
//        }

//        lifecycleScope.launch {
//            val response = api.deleteUser(2)
//            if (response.isSuccessful) {
//                Log.d("TAG", "onCreate: ${response.body()} ${response.code()}")
//            } else {
//                Log.d("TAG", "onCreate: Sorry something with creating ")
//            }


//        lifecycleScope.launch {
//            val response = api.getTodoList().body()
//            response?.apply {
//                todoAdapter.submitList(this)
//            }
//
//        }

        binding.recyclerView.adapter = userAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))

//            binding.recyclerView.adapter = todoAdapter
//            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//            binding.recyclerView.addItemDecoration(
//                DividerItemDecoration(
//                    this@MainActivity,
//                    LinearLayoutManager.VERTICAL
//                )
//            )
        val flow:Flow<PagingData<User>> = Pager(
            config = PagingConfig(pageSize = 3, enablePlaceholders = false),
            pagingSourceFactory = {UserPagingSource(api)}
        ).flow

        lifecycleScope.launch {
            flow.collect{
                userAdapter.submitData(it)

            }
        }
        lifecycleScope.launch {
            userAdapter.loadStateFlow.collect{
                binding.progress.isVisible = it.source.append is LoadState.Loading
            }
        }
    }
}