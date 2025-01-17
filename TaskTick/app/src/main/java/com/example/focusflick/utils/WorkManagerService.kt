package com.example.focusflick.utils

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class WorkManagerService(val context: Context) {
    fun schedule(name: String, delay: Long) {
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .addTag(name)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("name" to name))
            .build()

        //WorkManager.getInstance(context).enqueue(request)
        WorkManager.getInstance(context).enqueueUniqueWork(name, ExistingWorkPolicy.REPLACE, request)
    }
}