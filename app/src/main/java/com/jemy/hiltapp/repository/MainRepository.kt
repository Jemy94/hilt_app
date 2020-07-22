package com.jemy.hiltapp.repository

import com.jemy.hiltapp.model.Blog
import com.jemy.hiltapp.retrofit.BlogRetrofit
import com.jemy.hiltapp.retrofit.NetworkMapper
import com.jemy.hiltapp.room.BlogDao
import com.jemy.hiltapp.room.CacheMapper
import com.jemy.hiltapp.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class MainRepository constructor(
    private val blogDao: BlogDao,
    private val retrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = retrofit.getBlog()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cacheBlogs = blogDao.getBlog()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}