package com.example.myapplication.network

import android.util.Log
import com.example.myapplication.utils.ApiProcess
import com.example.myapplication.database.UserDao
import com.example.myapplication.model.Users
import com.example.myapplication.utils.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, private val userDao: UserDao) {

    fun <T> hitApi(type: TypeOfApi, request: ApiProcess<List<T>>) {
        val hitApi = flow {
            val response = request.sendRequest(when(type){
                TypeOfApi.USER_API -> apiService
            }) as Response<Any>
            emit(response)
        }.flowOn(Dispatchers.IO)

        CoroutineScope(Dispatchers.Main).launch {
            hitApi.catch {
                request.showLoader(false)
                it.message?.let { it1 -> request.failure(it1) }
            }.collect{response->
                request.showLoader(false)
                request.success(response)
            }
        }
    }

    suspend fun addUsers(users: Users) = userDao.addUserData(users)
    suspend fun deleteUsers(users: Users) = userDao.deleteUserData(users)
    suspend fun updateUsers(users: Users) = userDao.updateUserData(users)
    suspend fun deleteAllUsers() = userDao.deleteAllUserList()
    suspend fun getUserDetailsById(id: String):Users = userDao.getUserDetailsById(id)
    fun getAllUsers():Flow<List<Users>>  = userDao.getUserList().flowOn(Dispatchers.IO).conflate()



}


