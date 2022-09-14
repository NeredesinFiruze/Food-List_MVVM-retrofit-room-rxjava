package util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferences {

    companion object {

        private const val time = "TIME"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: util.SharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context): util.SharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: makeSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeSharedPreferences(context: Context): util.SharedPreferences {
            sharedPreferences =
                androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferences()

        }
    }

        fun recordTime(timing: Long){
            sharedPreferences?.edit(commit=true){
                putLong(time,timing)
            }
        }

        fun takeTime()= sharedPreferences?.getLong(time,0)

}