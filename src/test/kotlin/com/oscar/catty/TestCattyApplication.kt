package com.oscar.catty

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<CattyApplication>().with(TestcontainersConfiguration::class).run(*args)
}
