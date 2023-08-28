package mi.xi.timer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.entities.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class TimerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}