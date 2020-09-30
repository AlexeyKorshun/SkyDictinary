package com.alexeykorshun.android.skydictonary.extension

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Alexey Korshun on 30.09.2020.
 */
@Suppress("UNCHECKED_CAST")
fun <T : Any> Fragment.argument(
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Fragment, T> = object : ReadWriteProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return thisRef.arguments?.get(key.invoke(property)) as T
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args: Bundle = thisRef.arguments
            ?: Bundle().also(thisRef::setArguments)
        args.put(key.invoke(property), value)
    }
}

private fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Serializable -> putSerializable(key, value)
        is Parcelable -> putParcelable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}