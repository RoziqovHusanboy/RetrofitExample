package com.example.myapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

private const val STARTING_PAGE = 0

class UserPagingSource(val apiInterface: ApiInterface) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val startkey = params.key ?: STARTING_PAGE

        try {
            if (startkey!= STARTING_PAGE) delay(3000)
            val response = apiInterface.getAllUserByPage(startkey,params.loadSize)
                val userList = response.body()?.userList
                return LoadResult.Page(
                    userList ?: emptyList(),
                    prevKey = if (startkey == STARTING_PAGE) null else startkey - 1,
                    nextKey = if (userList == null || userList.isEmpty()) null else startkey + 1
                )

        }catch (e:Exception){
            return LoadResult.Error(e)
        }

    }
}