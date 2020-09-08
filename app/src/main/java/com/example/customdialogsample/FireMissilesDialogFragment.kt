package com.example.customdialogsample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FireMissilesDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val array = arrayOf("佐藤", "鈴木", "井上")
            builder.setTitle("title")
                .setItems(array,
                    DialogInterface.OnClickListener { dialog, which ->
                        // 'which'引数には、選択したアイテムのインデックス位置が含まれています
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}