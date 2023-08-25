package mi.xi.timer.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import mi.xi.timer.util.DispatcherProvider
import javax.inject.Inject

class DataManager @Inject constructor(
    @PublishedApi internal val dataStore: DataStore<Preferences>,
    private val dispatchers: DispatcherProvider
) {
    private val gsonConverter = Gson()

    suspend fun getString(key: String): String? =
        get { preferences -> preferences[stringPreferencesKey(key)] }

    suspend fun setString(key: String, value: String) {
        set { preferences -> preferences[stringPreferencesKey(key)] = value }
    }

    suspend inline fun <reified T> getObject(key: String): T? {
        val value = dataStore.data.first()[stringPreferencesKey(key)] ?: return null
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(value, type)
    }

    suspend fun <T> setObject(key: String, value: T) {
        set { preferences -> preferences[stringPreferencesKey(key)] = gsonConverter.toJson(value) }
    }

    // region Helper functions ---------------------------------------------------------------------

    private suspend fun getPreferences() = dataStore.data.first()

    private suspend fun <T> get(body: (Preferences) -> T) = withContext(dispatchers.io()) {
        body(getPreferences())
    }

    private suspend fun <T> set(body: (MutablePreferences) -> T) = withContext(dispatchers.io()) {
        dataStore.edit { preferences -> body(preferences) }
    }
    // endregion Helper functions ------------------------------------------------------------------
}