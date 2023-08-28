package mi.xi.timer.data.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task WHERE username=:username")
    suspend fun getAllFromUser(username: String?): List<Task>

    @Insert
    suspend fun insert(task: Task): Long
}