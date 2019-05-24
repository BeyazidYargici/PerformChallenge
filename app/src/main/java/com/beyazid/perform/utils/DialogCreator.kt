package com.beyazid.perform.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.beyazid.perform.MainActivity
import com.beyazid.perform.R
import com.crowdfire.cfalertdialog.CFAlertDialog

/**
 *  Created by beyazid on 2019-05-19.
 */

fun createDialog(context: Context, code: String, message: String) {
    val builder = CFAlertDialog.Builder(context)
        .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
        .setTitle("ERROR")
        .setMessage("response code : $code \n response message : $message \n'Restart PerformApp'")
        .addButton(
            "Close",
            -1,
            ContextCompat.getColor(context, R.color.abc_secondary_text_material_dark),
            CFAlertDialog.CFAlertActionStyle.POSITIVE,
            CFAlertDialog.CFAlertActionAlignment.END
        ) { dialog, which ->
//          a temporary solution, normally it shouldn't like this
            System.exit(0)
        }
    builder.show()
}
