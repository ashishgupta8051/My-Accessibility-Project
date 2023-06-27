package com.example.myapplication.database

import androidx.room.*
import com.example.myapplication.model.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserData(users: Users)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserData(users: Users)

    @Delete
    suspend fun deleteUserData(users: Users)

    @Query("SELECT * from users_table")
    fun getUserList(): Flow<List<Users>>

    @Query("SELECT * from users_table where id = :id")
    suspend fun getUserDetailsById(id: String): Users

    @Query("DELETE from users_table")
    suspend fun deleteAllUserList()






}