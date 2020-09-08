package com.example.customdialogsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        NoticeDialogFragment.NoticeDialogListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            showNoticeDialog()
        }

    }

    fun showNoticeDialog() {
        // ダイアログフラグメントのインスタンスを作成して表示します
        val dialog = NoticeDialogFragment()
        dialog.show(supportFragmentManager, "NoticeDialogFragment")
    }

    // ダイアログフラグメントは、Fragment.onAttach（）コールバックを通じてこのアクティビティへの参照を受け取ります。
    // このコールバックは、NoticeDialogFragment.NoticeDialogListenerインターフェースで定義された
    // 次のメソッドを呼び出すために使用します。
    override fun onDialogPositiveClick(dialog: DialogFragment, selectedItems: ArrayList<Int>) {
        // ユーザーがダイアログの正のボタンに触れた
        textView.text = selectedItems.toString()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        // ユーザーがダイアログの否定ボタンに触れた
        textView.text = "canceled"
    }

}

// 出典：https://developer.android.com/guide/topics/ui/dialogs?hl=ja