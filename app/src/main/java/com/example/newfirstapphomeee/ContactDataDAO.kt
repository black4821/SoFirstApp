package com.example.newfirstapphomeee

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDataDAO {
    @Insert
    fun insert(contact: ContactData)

    @Query("SELECT * from contact_table")
    fun get(): LiveData<List<ContactData>>
}