package com.example.norwinguerrero.itaccess;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.norwinguerrero.itaccess.helper.DatabaseHandler;
import com.example.norwinguerrero.itaccess.helper.DialogoAlerta;
import com.example.norwinguerrero.itaccess.pojo.Access;
import com.example.norwinguerrero.itaccess.report.TodayAccess;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Norwin Guerrero on 7/2/2016.
 */
public class CreateActivity extends BaseActivity {

    Spinner txtescort;
    Button btnsave, btnphoto;
    EditText txtdate, txtfullname, txtcompany, txtpourpose, txtsignature, txttimein, txttimeout;
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    String Toast_msg;
    final Context context = this;

    ImageView iv;
    Bitmap bp = null;

    private static final String[] paths = {"Bismark Antonio Carmona Gaitán", "Cristobal Santiago Huerta Espinal",
            "Elliot Cesar Velazquez Gutiérrez", "Erick Alberto Alemán Castillo", "Julio Alejandro Bendaña Garcia",
            "Manuel Antonia Calonge Castillo", "Mario Enrique Loaisiga Tapia", "Melvin Jose Silva Urroz", "Oscar Antonio Parrales Rivera",
            "Rudy Hilmer Taylor Gonzalez", "Rudy Moises Putoy Gonzalez"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        txtescort = (Spinner) findViewById(R.id.txtescort);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateActivity.this,android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtescort.setAdapter(adapter);

        saveAccess();
    }

    private void saveAccess() {
        btnsave = (Button) findViewById(R.id.bntsave);
        txtdate = (EditText) findViewById(R.id.txtdate);
        txtfullname = (EditText) findViewById(R.id.txtfullname);
        txtcompany = (EditText) findViewById(R.id.txtcompany);
        txtpourpose = (EditText) findViewById(R.id.txtpourpose);
        txttimein = (EditText) findViewById(R.id.txttimein);
        txttimeout = (EditText) findViewById(R.id.txttimeout);
        txtsignature = (EditText) findViewById(R.id.txtsignature);
        btnphoto  = (Button) findViewById(R.id.btnphoto);

        iv=(ImageView)findViewById(R.id.imageView);


        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
        txtdate.setText(ft.format(now));

        txtfullname.requestFocus();


        btnphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate() == true) {

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String encodedImageString = Base64.encodeToString(b, Base64.DEFAULT);
                    byte[] bytarray = Base64.decode(encodedImageString, Base64.DEFAULT);


                    Access access = new Access();

                    access.set_date(txtdate.getText().toString());
                    access.set_fullname(txtfullname.getText().toString());
                    access.set_company(txtcompany.getText().toString());
                    access.set_purposeofvisit(txtpourpose.getText().toString());
                    access.set_signature(txtsignature.getText().toString());
                    access.set_timein(txttimein.getText().toString());
                    access.set_timeout(txttimeout.getText().toString());
                    access.set_escort(txtescort.getSelectedItem().toString());


                    access.set_photo(bytarray);

                    dbHandler.addContact(access);
                    Clear();
                    Toast_msg = "Data inserted successfully";
                    Show_Toast(Toast_msg);

                    Intent intent = new Intent(context, TodayAccess.class);
                    startActivity(intent);

                } else {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogoAlerta dialogo = new DialogoAlerta("Show Input Required!!");
                    dialogo.show(fragmentManager, "tagAlerta");
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }

    private boolean validate(){
        if (txtfullname.getText().toString().isEmpty() || txtcompany.getText().toString().isEmpty() || txtpourpose.getText().toString().isEmpty() || txtsignature.getText().toString().isEmpty() || txttimein.getText().toString().isEmpty() || txttimeout.getText().toString().isEmpty()) {
            return false;
        }else{
            return  true;
        }

    }

   private void Clear(){
       txtdate.setText("");
       txtfullname.setText("");
       txtcompany.setText("");
       txtpourpose.setText("");
       txtsignature.setText("");
       txttimein.setText("");
       txttimeout.setText("");
   }

    private void Show_Toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
