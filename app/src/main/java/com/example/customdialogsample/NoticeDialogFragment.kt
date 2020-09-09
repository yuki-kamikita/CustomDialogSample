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
        fun onDialogPositiveClick(dialog: DialogFragment, selectedItem: Map<Int,String>)
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
            val selectedIndex = ArrayList<Int>() // 何番目のものを選んだか
            val selectedItem = mutableMapOf<Int, String>()
            val arrayName = arrayOf("佐藤", "鈴木", "高橋", "田中", "伊藤", "渡辺", "山本", "中村", "小林", "加藤", "吉田", "山田", "佐々木", "山口", "松本")
            val arrayId = arrayOf(11,12,13,14,15,16,17,18,19,20,21,22,23,24,25)
            val builder = AlertDialog.Builder(it)
            // ダイアログのタイトルを設定する
            builder.setTitle("Title")
                // リスト配列、デフォルトで選択される項目（なしの場合はnull）、
                // および項目が選択されたときにコールバックを受信するリスナーを指定します
                .setMultiChoiceItems(arrayName, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            // ユーザーがアイテムをチェックした場合、選択したアイテムに追加します
                            selectedIndex.add(which)
                            selectedItem[arrayId[which]] = arrayName[which]
                        } else if (selectedIndex.contains(which)) {
                            // それ以外の場合、項目がすでに配列にある場合は、それを削除します
                            selectedIndex.remove(Integer.valueOf(which))
                            selectedItem.remove(arrayId[which])
                        }
                    }
                )
                // アクションボタンを設定する
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        // ユーザーが[OK]をクリックしたので、selectedItemsの結果をどこかに保存するか、
                        // ダイアログを開いたコンポーネントに返します
                        listener.onDialogPositiveClick(this, selectedItem)
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