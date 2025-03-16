package com.oscar.catty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CattyApplication

fun main(args: Array<String>) {
    runApplication<CattyApplication>(*args)
}
