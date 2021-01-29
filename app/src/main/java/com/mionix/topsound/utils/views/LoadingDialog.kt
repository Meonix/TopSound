package com.mionix.topsound.utils.views

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.mionix.topsound.R

class LoadingDialog(private val activity:Activity) {
    private lateinit var alertDialog: AlertDialog
    fun startLoadingDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog,null))
        builder.setCancelable(true)
        alertDialog = builder.create()
        alertDialog.show()
    }
    fun dismissDialog(){
        alertDialog.dismiss()
    }
}