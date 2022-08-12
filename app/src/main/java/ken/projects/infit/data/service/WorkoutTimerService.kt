package ken.projects.infit.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewModelScoped
import ken.projects.infit.MainActivity
import ken.projects.infit.R
import ken.projects.infit.domain.WorkoutRepository
import ken.projects.infit.util.getTimeStringFromDouble
import ken.projects.infit.viewmodel.WorkoutViewModel
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WorkoutTimerService: Service() {



    override fun onBind(p0: Intent?): IBinder? = null

    private val timer: Timer = Timer()
    private var isTimerRunning = false

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val timeElapsed = intent.getDoubleExtra(TIME_ELAPSED, 0.0)
        timer.scheduleAtFixedRate(TimeTask(timeElapsed), 0, 1000)
        isTimerRunning = intent.getBooleanExtra(TIMER_RUNNING,false)
        return START_NOT_STICKY
    }

    private inner class TimeTask (private var timeElapsed: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(TIMER_UPDATED)
            timeElapsed++
            startForegroundService(timeElapsed)
            intent.putExtra(TIME_ELAPSED, timeElapsed)
            intent.putExtra(TIMER_RUNNING,isTimerRunning)
            sendBroadcast(intent)
        }

    }

    override fun stopService(name: Intent?): Boolean {
        isTimerRunning = false
        return super.stopService(name)
    }

    override fun onDestroy() {
        timer.cancel()
        isTimerRunning = false
        super.onDestroy()
    }

    private fun startForegroundService(timeElapsed: Double) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)

        val time = getTimeStringFromDouble(timeElapsed)

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setContentTitle("ongoing workout")
            .setContentText(time)
            .setSmallIcon(R.drawable.ic_timer)
           // .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

    private fun getMainActivityPendingIntent() =
        PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(this, MainActivity::class.java)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .setPackage(null)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED )
            ,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,

            )


    companion object {

        const val TIMER_UPDATED = "timerUpdated"
        const val TIMER_RUNNING = "isTimerRunning"
        const val TIME_ELAPSED = "timeElapsed"
        const val NOTIFICATION_CHANNEL_ID = "notification_channel"
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_CHANNEL_NAME = "notification"
    }
}