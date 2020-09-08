package com.example.customdialogsample

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NoticeDialogFragment : DialogFragment() {
    // インターフェースのこのインスタンスを使用して、アクションイベントを配信します
    internal lateinit var listener: NoticeDialogListener

    /* このダイアログフラグメントのインスタンスを作成するアクティビティは、
     * イベントコールバックを受信するために、このインターフェイスを実装する必要があります。
     * ホストがクエリする必要がある場合に備えて、各メソッドはDialogFragmentを渡します。 */
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment ,selectedItems: ArrayList<Int>)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // Fragment.onAttach（）メソッドをオーバーライドして、NoticeDialogListenerをインスタンス化します。
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // ホストアクティビティがコールバックインターフェイスを実装していることを確認する
        try {
            // ホストにイベントを送信できるように、NoticeDialogListenerをインスタンス化します
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // アクティビティはインターフェースを実装していません、例外をスローします
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

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
                        listener.onDialogPositiveClick(this, selectedItems)
                    }
                )
                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogNegativeClick(this)
                    }
                )

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}