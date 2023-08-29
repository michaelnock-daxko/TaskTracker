package mi.xi.timer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mi.xi.timer.data.TimerDatabase
import mi.xi.timer.data.entities.TaskDao
import mi.xi.timer.data.entities.TaskEventDao
import mi.xi.timer.util.TimeManager
import mi.xi.timer.util.TimeManagerImpl

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun taskDao(database: TimerDatabase): TaskDao = database.taskDao()

    @Provides
    fun taskEventDao(database: TimerDatabase): TaskEventDao = database.taskEventDao()

    @Provides
    fun timerManager(): TimeManager = TimeManagerImpl()
}