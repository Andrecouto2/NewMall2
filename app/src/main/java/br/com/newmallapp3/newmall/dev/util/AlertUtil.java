package br.com.newmallapp3.newmall.dev.util;

import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class AlertUtil {

    public static AlertDialog createAlertDialog(Context context, String title, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });


        final AlertDialog alert = dialog.create();
        return alert;
    }
}
