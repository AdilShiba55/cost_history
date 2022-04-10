package com.example.costhistory.units

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object ToastUnit {
    const val VALIDATION_TITLE = "Валидация Формы"
    const val VALIDATION_MSG = "Заполните обязательные поля."
    const val VALIDATION_NOT_EMPTY = "Поле не может быть пустым"

    fun fieldsValidationMessage(fields: List<String>): String {
        var message = "Заполните пол${if(fields.size == 1) "е" else "я"}:\n"
        message += fields.joinToString(separator = ", ")
        return message
    }

    fun showInfo(context: Context, title: String, message: String) {
        makeToast(
            context = context, title = title, message = message,
            motionToastStyle = MotionToastStyle.INFO
        )
    }

    fun showSuccess(context: Context, title: String, message: String) {
        makeToast(
            context = context, title = title, message = message,
            motionToastStyle = MotionToastStyle.SUCCESS
        )
    }

    private fun makeToast(
        context: Context, title: String, message: String,
        motionToastStyle: MotionToastStyle
    ) {
        MotionToast.darkColorToast(
            context = context as Activity,
            title = title,
            message = message,
            style = motionToastStyle,
            position = MotionToast.GRAVITY_BOTTOM,
            duration = MotionToast.LONG_DURATION,
            font = ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
        )
    }
}