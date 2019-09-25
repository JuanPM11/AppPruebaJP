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

public class Municipio extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    /// Se inicializa los botones, cajas de texto, lista, array, spinner y base de datos
    DataBaseHelper myDB;
    Button back, save, delete, edit;
    TextView etNombreMun, etCodMun;
    EditText etIdMun;
    ArrayAdapter<String> comboAdapterSql;
    List<String> listaDepartamentosSql;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio);

        etNombreMun = (EditText) findViewById(R.id.etNombreMun);
        etCodMun = (EditText) findViewById(R.id.etCodMun);
        etIdMun = (EditText) findViewById(R.id.etIdMun);

        // Contexto de la base de datos
        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);

        //// Se obtiene el Id de los botones, cajas de texto, spinner y base de datos
        save = (Button) findViewById(R.id.btnSave);
        back = (Button) findViewById(R.id.back);
        delete = (Button) findViewById(R.id.btnDelete);
        edit = (Button) findViewById(R.id.btnEdit);

        myDB = DataBaseHelper.getInstance(this);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        listaDepartamentosSql = new ArrayList<>();


        int sizeListaDepartamentos = myDB.getAllDepartamentos().size();
        for (int i = 0; i < sizeListaDepartamentos; i++) {
            listaDepartamentosSql.add(myDB.getAllDepartamentos().get(i).getNombreDepartamentos());
        }


        comboAdapterSql = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDepartamentosSql);
        spinner.setAdapter(comboAdapterSql);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(Municipio.this, editar_municipio.class);
                startActivity(edit);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Municipio.this, MainActivity.class);
                startActivity(back);
            }
        });

        save();


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(Municipio.this, EliminarMunicipios.class);
                startActivity(delete);
            }
        });

    }

    /**
     * METODO PARA AGREGAR REGISTROS
     */
    public void save() {
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertDataMunicipios(etNombreMun.getText().toString(),
                                etCodMun.getText().toString(), spinner.getSelectedItem().toString());

                        if (isInserted = true)

                            Toast.makeText(Municipio.this, "GUARDADO", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Municipio.this, "NO SE GUARDO", Toast.LENGTH_LONG).show();
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
