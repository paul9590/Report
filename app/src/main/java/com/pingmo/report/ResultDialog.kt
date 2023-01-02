package com.pingmo.report

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.pingmo.report.databinding.DialResultBinding

class ResultDialog(context: Context, title: String) {
    private val dial = Dialog(context)
    private var mBinding: DialResultBinding

    private var width = ActionBar.LayoutParams.MATCH_PARENT
    private var height = ActionBar.LayoutParams.MATCH_PARENT

    init {
        dial.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mBinding = DialResultBinding.inflate(LayoutInflater.from(context)).apply {
            txtTitle.text = title
            btnBack.setOnClickListener {
                dismiss()
            }
        }
        setSize(width = (context.resources.displayMetrics.widthPixels * 0.8).toInt(),
            height = (context.resources.displayMetrics.heightPixels * 0.3).toInt())
    }

    fun show() {
        initDial()
        dial.show()
    }

    private fun initDial() {
        dial.setContentView(mBinding.root)
        dial.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setSize(width, height)
        dial.window?.setLayout(width, height)
    }

    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    fun dismiss() {
        dial.dismiss()
    }
}