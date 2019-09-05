package com.example.kotlin_singleton_example

import com.example.kotlin_singleton_example.models.User

object ExampleSingleton {
    val singletonUser: User by lazy {
        User("shrinidhi99.varna@gmail.com", "shrinidhi", "image.png")
    }
}