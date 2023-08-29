package mi.xi.timer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.entities.TaskDao
import mi.xi.timer.data.entities.TaskEvent
import mi.xi.timer.data.entities.TaskEventDao

@Database(entities = [Task::class, TaskEvent::class], version = 3)
abstract class TimerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun taskEventDao(): TaskEventDao
}