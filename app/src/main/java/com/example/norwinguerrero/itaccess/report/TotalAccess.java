package com.example.norwinguerrero.itaccess.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.norwinguerrero.itaccess.BaseActivity;
import com.example.norwinguerrero.itaccess.R;
import com.example.norwinguerrero.itaccess.helper.DatabaseHandler;
import com.example.norwinguerrero.itaccess.pojo.Access;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Norwin Guerrero on 7/2/2016.
 */
public class TotalAccess extends BaseActivity {
    private ListView lstOpciones;
    DatabaseHandler dbHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Access Total");
        setContentView(R.layout.report_today);


        lstOpciones = (ListView) findViewById(R.id.LstOpciones);

        List<Access> contact = dbHandler.getAccess();

        ArrayList<String> listItems = new ArrayList<>();

        for (Access access : contact) {
            String date = access.get_date();
            String fullname = access.get_fullname();
            String company = access.get_company();
            String sigmature = access.get_signature();
            String escort = access.get_escort();
            String Name_num = "Date: " + date + " / Name: " + fullname + " / Company: " + company + " / Signature: " + sigmature + " / Escort: " +escort;
            listItems.add(Name_num);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_access, R.id.listText, listItems);
        lstOpciones.setAdapter(adapter);

        lstOpciones.setOnItemClickListener(new ListClickHandler());
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView listText = (TextView) view.findViewById(R.id.listText);
            String text = listText.getText().toString();
            Intent intent = new Intent(TotalAccess.this, ViewAccess.class);
            intent.putExtra("selected-item", text);
            startActivity(intent);
        }
    }
}
