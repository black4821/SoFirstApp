package com.example.newfirstapphomeee

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class ContactData (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "name")
    var name: String = "unnamed",
    @ColumnInfo(name = "phone")
    var phone: String = ""
)