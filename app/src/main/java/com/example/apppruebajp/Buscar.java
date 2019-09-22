package com.example.apppruebajp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.lang.reflect.Array;

public class Buscar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button Back, search;
    DataBaseHelper myDB;
    EditText searchIdMun, searchName, searchCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        Back = (Button) findViewById(R.id.back);
        myDB = new DataBaseHelper(getApplicationContext());
        search = (Button) findViewById(R.id.btnSeatchMun);
        searchIdMun = (EditText) findViewById(R.id.searchIdMun);
        searchName = (EditText) findViewById(R.id.searchName);
        searchCod = (EditText) findViewById(R.id.searchCod);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(Buscar.this, MainActivity.class);
                startActivity(Back);
            }
        });
        search();
    }

    public void search() {
        search.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String cod = searchIdMun.getText().toString();
                        String[] isSearch = myDB.BuscarDept(cod);
                        searchName.setText(isSearch[0]);
                        searchCod.setText(isSearch[1]);
                        Toast.makeText(Buscar.this, "Encontrado!", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
