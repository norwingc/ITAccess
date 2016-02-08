package com.example.norwinguerrero.itaccess.report;

import android.os.Bundle;

import com.example.norwinguerrero.itaccess.BaseActivity;
import com.example.norwinguerrero.itaccess.R;

/**
 * Created by Norwin Guerrero on 7/2/2016.
 */
public class ReportAccess extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Report ITAccess");
        setContentView(R.layout.report_activity);
    }
}
