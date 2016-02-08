package com.example.norwinguerrero.itaccess.helper;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Norwin Guerrero on 7/2/2016.
 */
public class DialogoAlerta extends DialogFragment {

    public String msj;

    public DialogoAlerta(String msj) {
        this.msj = msj;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage(this.msj)
                .setTitle("Information")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}