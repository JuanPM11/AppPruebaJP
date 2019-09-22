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


        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);

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


        myDB = new DataBaseHelper(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(Municipio.this, MainActivity.class);
                startActivity(Back);
            }
        });

        save();
        delete();
        update();

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

    /**
     * METODO PARA ELIMINAR  REGISTROS
     */
    public void delete() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteMunicipios(etIdMun.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(Municipio.this, "Eliminado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Municipio.this, "Fallo al Eliminar", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /**
     * METODO PARA EDITAR REGISTRO
     */
    public void update() {
        edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDB.updateMunicipios(etIdMun.getText().toString(), etNombreMun.getText().toString(), etCodMun.getText().toString());
                        if (isUpdate)
                            Toast.makeText(Municipio.this, "Editado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Municipio.this, "Fallo al editar", Toast.LENGTH_LONG).show();

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
