package com.example.homework.domain.helpers

import java.lang.reflect.Field

class LayoutHelper {
     fun getLayoutBackgroundDrawableId(resourceName: String, c: Class<*>): Int {
        try {
            val idField: Field = c.getDeclaredField(resourceName)
            return idField.getInt(idField)
        } catch (e: Exception) {
            throw RuntimeException(
                    "No resource ID found for: $resourceName / $c, $e"
            )
        }
    }

}