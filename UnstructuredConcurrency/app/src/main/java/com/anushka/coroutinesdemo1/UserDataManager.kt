package com.anushka.coroutinesdemo1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager {

    suspend fun getTotalUserCount(): Int {
        var count = 0

        CoroutineScope(IO).launch {
            delay(1000)
            count = 50
        }

        val result = CoroutineScope(IO).async {
            delay(3000)
            70
        }

        return count + result.await()
    }
}