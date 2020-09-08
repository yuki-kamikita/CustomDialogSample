package com.example.customdialogsample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FireMissilesDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Builderクラスを使用して、ダイアログを簡単に構築する
            val builder = AlertDialog.Builder(it)
            builder.setMessage("text")
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                    })
                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // AlertDialogオブジェクトを作成して返す
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}