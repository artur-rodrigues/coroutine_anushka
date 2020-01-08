package com.anushka.roomdemo.db

class SubscriberRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) {
        dao.insert(subscriber)
    }

    suspend fun clearAll() {
        dao.deleteAll()
    }
}