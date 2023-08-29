package mi.xi.timer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEvent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val taskId: Long,
    val millis: Long,
    val creationDate: Long
)