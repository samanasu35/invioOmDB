package com.volkanalyanak.omdbex.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.volkanalyanak.omdbex.R;

public class LoadDialog {

    Activity act;
    AlertDialog dialog;
    TextView txt_pb;

    public LoadDialog(Activity act)
    {
        this.act = act;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.act);
        LayoutInflater infLayout = this.act.getLayoutInflater();
        View v = infLayout.inflate(R.layout.load_dialog,null);
        txt_pb = v.findViewById(R.id.txt_pb);
        builder.setView(v);
        builder.setCancelable(false);
        dialog = builder.create();
    }

    public void showDialog()
    {
        dialog.show();
    }
    public void dismissDialog()
    {
        dialog.dismiss();
    }
    public void setPbValue(String val)
    {
        txt_pb.setText(val);
    }
}
