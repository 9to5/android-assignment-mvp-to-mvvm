package software.ninetofive.assignment_tests.utils

import android.content.Context
import android.content.SharedPreferences
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.ninetofive.assignment_tests.main.SelectedScreen
import software.ninetofive.assignment_tests.main.ViewingOption

class AppPreferencesTest {

    @Test
    fun defaultSelectedScreen() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }

    @Test
    fun selectedScreenA() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setStartScreen(SelectedScreen.SCREEN_A)

        assertEquals(SelectedScreen.SCREEN_A, appPreferences.getStartScreen())
    }

    @Test
    fun selectedScreenB() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setStartScreen(SelectedScreen.SCREEN_B)

        assertEquals(SelectedScreen.SCREEN_B, appPreferences.getStartScreen())
    }

    @Test
    fun selectedScreenC() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setStartScreen(SelectedScreen.SCREEN_C)

        assertEquals(SelectedScreen.SCREEN_C, appPreferences.getStartScreen())
    }

    @Test
    fun defaultViewingOption() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        assertEquals(ViewingOption.NOTHING, appPreferences.getViewingOption())
    }

    @Test
    fun selectedShowName() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setViewingOption(ViewingOption.SHOW_NAME)

        assertEquals(ViewingOption.SHOW_NAME, appPreferences.getViewingOption())
    }

    @Test
    fun selectedDate() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setViewingOption(ViewingOption.DATE)

        assertEquals(ViewingOption.DATE, appPreferences.getViewingOption())
    }

    @Test
    fun selectedNothing() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setViewingOption(ViewingOption.NOTHING)

        assertEquals(ViewingOption.NOTHING, appPreferences.getViewingOption())
    }

    @Test
    fun defaultShowValidDot() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        assertEquals(false, appPreferences.shouldShowValidDot())
    }

    @Test
    fun toggleShowValidDot() {
        val appPreferences = TestableAppPreferences(InMemorySharedPreferences())

        appPreferences.setShowValidDot(true)

        assertEquals(true, appPreferences.shouldShowValidDot())
    }

    private class TestableAppPreferences(
        val sharedPreferences: InMemorySharedPreferences
    ) : AppPreferences(null, sharedPreferences) {

        override fun getPreferences(context: Context?): SharedPreferences? {
            return sharedPreferences
        }
    }

    private class InMemorySharedPreferences : SharedPreferences {

        private val valuesMap = HashMap<String, Any?>()
        private val uncommittedValuesMap = HashMap<String, Any?>()
        private val editor = Editor(valuesMap, uncommittedValuesMap)

        override fun getAll(): MutableMap<String, *> {
            TODO("Not yet implemented")
        }

        override fun getString(key: String?, defaultValue: String?): String? {
            return valuesMap.getOrDefault(key, defaultValue) as? String?
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
            return valuesMap.getOrDefault(key, defaultValue) as Boolean
        }

        override fun contains(key: String?): Boolean {
            TODO("Not yet implemented")
        }

        override fun edit(): SharedPreferences.Editor = editor

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

        inner class Editor(
            private val valuesMap: MutableMap<String, Any?>,
            private val uncommittedValuesMap: MutableMap<String, Any?>
        ) : SharedPreferences.Editor {

            override fun putString(key: String, value: String?): SharedPreferences.Editor {
                uncommittedValuesMap[key] = value
                return this
            }

            override fun putStringSet(
                key: String,
                value: MutableSet<String>?
            ): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
                uncommittedValuesMap[key] = value
                return this
            }

            override fun remove(key: String): SharedPreferences.Editor {
                uncommittedValuesMap.remove(key)
                return this
            }

            override fun clear(): SharedPreferences.Editor {
                uncommittedValuesMap.clear()
                valuesMap.clear()
                return this
            }

            override fun commit(): Boolean {
                uncommittedValuesMap.forEach {
                    valuesMap[it.key] = it.value
                }
                uncommittedValuesMap.clear()
                return true
            }

            override fun apply() {
                commit()
            }
        }
    }
}