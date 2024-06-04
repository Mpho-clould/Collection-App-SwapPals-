package com.example.swappals.model

import java.io.Serializable

class Category (val name: String, val goalItem: Int) : Serializable {
    var id: Long = 0
}