package com.example.customdialogsample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FireMissilesDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>() // 選択したアイテムを追跡する場所
            val builder = AlertDialog.Builder(it)
            val array = arrayOf("佐藤", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中", "鈴木", "田中")
            // ダイアログのタイトルを設定する
            builder.setTitle("Title")
                // リスト配列、デフォルトで選択される項目（なしの場合はnull）、
                // および項目が選択されたときにコールバックを受信するリスナーを指定します
                .setMultiChoiceItems(array, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            // ユーザーがアイテムをチェックした場合、選択したアイテムに追加します
                            selectedItems.add(which)
                        } else if (selectedItems.contains(which)) {
                            // それ以外の場合、項目がすでに配列にある場合は、それを削除します
                            selectedItems.remove(Integer.valueOf(which))
                        }
                    }
                )
                // アクションボタンを設定する
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        // ユーザーが[OK]をクリックしたので、selectedItemsの結果をどこかに保存するか、
                        // ダイアログを開いたコンポーネントに返します
                    }
                )
                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                    }
                )

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}