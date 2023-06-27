package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.utils.ApiProcess
import com.example.myapplication.model.Users
import com.example.myapplication.network.Repository
import com.example.myapplication.network.TypeOfApi
import com.example.myapplication.utils.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UsersVM @Inject constructor(private val repository: Repository) : ViewModel(){
    private val onlineUsers = MutableLiveData<List<Users>>()
    val getOnlineUsers: LiveData<List<Users>>
        get() = onlineUsers
    private var offlineUsers = MutableLiveData<List<Users>>()
    val getOfflineUsers: LiveData<List<Users>>
        get() = offlineUsers
    var mLoder = MutableLiveData<Boolean>()
    var userDetails : Users = Users(0,0,"", "")


    init {
        getUsersDataOnline()
        getUsersDataOffline()
    }

    private fun getUsersDataOnline() = viewModelScope.launch(Dispatchers.IO){
        repository.hitApi(type = TypeOfApi.USER_API,object : ApiProcess<List<Users>> {
            override fun showLoader(loader: Boolean) {
                mLoder.value = loader
            }

            override suspend fun sendRequest(apiService: ApiService): Response<List<Users>> {
                return apiService.getUsersList()
            }

            override fun success(response: Response<Any>) {
                onlineUsers.value = response.body() as MutableList<Users>
            }

            override fun failure(message: String) {

            }
        })
    }

    private fun getUsersDataOffline() = viewModelScope.launch(Dispatchers.Main){
        repository.getAllUsers().distinctUntilChanged().collect{
                list->
            offlineUsers.value = list
        }
    }

    fun addUsers(users: Users) = viewModelScope.launch(Dispatchers.IO) {
        repository.addUsers(users)
    }
    fun deleteUsers(users: Users) = viewModelScope.launch {
        repository.deleteUsers(users)
    }
    fun updateUsers(users: Users) = viewModelScope.launch {
        repository.updateUsers(users)
    }
    fun deleteAllUsers() = viewModelScope.launch {
        repository.deleteAllUsers()
    }
    fun getUserDetailsById(id: String) = viewModelScope.launch(Dispatchers.Main) {
        userDetails = repository.getUserDetailsById(id)
    }

}