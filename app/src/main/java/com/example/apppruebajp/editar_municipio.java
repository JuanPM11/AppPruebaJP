package com.example.apppruebajp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class editar_municipio extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    DataBaseHelper myDB;
    Button back, edit;
    EditText edtNombre, edtCod;
    ArrayAdapter<String> comboAdapterMunSql;
    List<String> listaMunicipiosSql;
    Spinner spinner2;
    String idMunicipios, nombreMunicipios, codigoMunicipios, departamentosID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_municipio);

        edtNombre = (EditText) findViewById(R.id.nombreMun);
        edtCod = (EditText) findViewById(R.id.codMun);
        // Contexto de la base de datos
        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);

        //// Se obtiene el Id de los botones, cajas de texto, spinner y base de datos
        back = (Button) findViewById(R.id.back);
        edit = (Button) findViewById(R.id.btnEditMun);

        myDB = DataBaseHelper.getInstance(this);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        listaMunicipiosSql = new ArrayList<>();

        int sizeListaMunicipios = myDB.getAllMunicipios().size();
        for (int i = 0; i < sizeListaMunicipios; i++) {
            listaMunicipiosSql.add(myDB.getAllMunicipios().get(i).getNombreMunicipios());
        }


        comboAdapterMunSql = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaMunicipiosSql);
        spinner2.setAdapter(comboAdapterMunSql);

        myDB = new DataBaseHelper(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(editar_municipio.this, Municipio.class);
                startActivity(Back);
            }
        });

        update();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner2:

                idMunicipios = myDB.getAllMunicipios().get(position).getId();

                nombreMunicipios = myDB.getAllMunicipios().get(position).getNombreMunicipios();
                codigoMunicipios = myDB.getAllMunicipios().get(position).getNombreMunicipios();

                Toast.makeText(this, "Id municipio: " + idMunicipios + " - Nombre municipio: " + nombreMunicipios + " - Codigo municipio: " + codigoMunicipios, Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /**
     * METODO PARA EDITAR REGISTRO
     */
    public void update() {
        edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDB.updateMunicipios( spinner2.getSelectedItem().toString() ,edtNombre.getText().toString(), edtCod.getText().toString());
                        if (isUpdate)
                            Toast.makeText(editar_municipio.this, "Editado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(editar_municipio.this, "Fallo al editar", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
