package mi.xi.timer.data.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskEventDao {
    @Query("SELECT * FROM taskevent WHERE taskId=:taskId")
    suspend fun getAllForTask(taskId: Long): List<TaskEvent>

    @Query("SELECT * FROM taskevent WHERE id=:id LIMIT 1")
    suspend fun getTask(id: Long): TaskEvent?

    @Insert
    suspend fun insert(taskEvent: TaskEvent): Long
}