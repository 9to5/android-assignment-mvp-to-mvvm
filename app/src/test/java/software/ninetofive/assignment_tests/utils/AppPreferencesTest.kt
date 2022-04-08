package software.ninetofive.assignment_tests.utils

import android.content.Context
import android.content.SharedPreferences
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen

class AppPreferencesTest {

    @Test
    fun instantiate() {
        val appPreferences = TestableAppPreferences()

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }

    class TestableAppPreferences : AppPreferences(null) {

        override fun getPreferences(context: Context?): SharedPreferences? {
            return InMemorySharedPreferences()
        }
    }

    private class InMemorySharedPreferences : SharedPreferences {

        override fun getAll(): MutableMap<String, *> {
            TODO("Not yet implemented")
        }

        override fun getString(key: String?, defaultValue: String?): String? {
            return ""
        }

        override fun getStringSet(
            key: String?,
            defaultValue: MutableSet<String>?
        ): MutableSet<String>? {
            TODO("Not yet implemented")
        }

        override fun getInt(key: String?, defaultValue: Int): Int {
            TODO("Not yet implemented")
        }

        override fun getLong(key: String?, defaultValue: Long): Long {
            TODO("Not yet implemented")
        }

        override fun getFloat(key: String?, defaultValue: Float): Float {
            TODO("Not yet implemented")
        }

        override fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
            TODO("Not yet implemented")
        }

        override fun contains(key: String?): Boolean {
            TODO("Not yet implemented")
        }

        override fun edit(): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun registerOnSharedPreferenceChangeListener(
            listener: SharedPreferences.OnSharedPreferenceChangeListener?
        ) {
            TODO("Not yet implemented")
        }

        override fun unregisterOnSharedPreferenceChangeListener(
            listener: SharedPreferences.OnSharedPreferenceChangeListener?
        ) {
            TODO("Not yet implemented")
        }
    }
}