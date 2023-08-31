package mi.xi.timer.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM task WHERE username=:username")
    suspend fun getAllFromUser(username: String?): List<Task>

    @Query("SELECT * FROM task WHERE id=:taskId LIMIT 1")
    suspend fun getTask(taskId: Long): Task?

    @Update
    suspend fun update(task: Task)

    @Insert
    suspend fun insert(task: Task): Long

    @Delete
    suspend fun delete(task: Task)
}