package com.crazylegend.kotlinextensions.core


/**
 * Created by hristijan on 6/7/19 to long live and prosper !
 */

fun Any.toUnsafeInt(): Int {
    return when (val value = this) {
        is String -> {
            value.toInt()
        }
        is Int -> {
            value
        }
        is Float -> {
            value.toInt()
        }
        is Double -> {
            value.toInt()
        }
        is Long -> {
            value.toInt()
        }
        else -> throw NumberFormatException()
    }
}

fun <T> Any.asType(): T? {
    return (this as? T)
}

fun Any.toNumberOrNull(): Number? {
    return when (this) {
        is Number -> {
            this
        }
        is String -> {
            when {
                this.toIntOrNull() != null -> this as Number
                this.toLongOrNull() != null -> this as Number
                this.toFloatOrNull() != null -> this as Number
                else -> null
            }
        }
        else -> null
    }
}

fun Any.toBooleanOrNull() = when {
    this is String -> {
        when {
            this.equals("true", true) -> true
            this.equals("false", true) -> false
            this.equals("1", true) -> true
            this.equals("0", true) -> false
            else -> null
        }
    }
    this is Boolean -> this
    this is Number -> {
        when {
            this == 1 -> true
            this == 0 -> false
            else -> null
        }
    }
    else -> null
}

inline fun <reified T : Enum<T>> enumSafeValueOf(name: String): T? {
    return try {
        enumValueOf<T>(name)
    } catch (e: Exception) {
        null
    }
}

