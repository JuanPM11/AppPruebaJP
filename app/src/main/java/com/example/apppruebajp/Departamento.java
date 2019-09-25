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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Departamento extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    /// Se inicializa los botones, cajas de texto y base de datos
    DataBaseHelper myDB;
    Button Back, save, delete, edit;
    EditText etName, etCod, id;
    ArrayAdapter<String> comboAdapter;
    List<String> listaDepartamentosSQL;
    Spinner spinnerDeptos;
    String idDepartamentos, nombreDepartamentos, codigoDepartamentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento);

        //// Se obtiene el Id de los botones, cajas de texto y base de datos
        etName = (EditText) findViewById(R.id.etName);
        etCod = (EditText) findViewById(R.id.etCod);


        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);


        save = (Button) findViewById(R.id.btnSave);
        edit = (Button) findViewById(R.id.btnEdit);
        delete = (Button) findViewById(R.id.btnDelete);
        Back = (Button) findViewById(R.id.back);
        spinnerDeptos = (Spinner) findViewById(R.id.spinnerDeptos);
        listaDepartamentosSQL = new ArrayList<>();

        int sizeListaDepartamentos = myDB.getAllDepartamentos().size();
        for (int i = 0; i < sizeListaDepartamentos; i++) {
            listaDepartamentosSQL.add(myDB.getAllDepartamentos().get(i).getNombreDepartamentos());
        }
        comboAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDepartamentosSQL);
        spinnerDeptos.setAdapter(comboAdapter);

        // Acción de regresar con el Botón Back
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(Departamento.this, MainActivity.class);
                startActivity(Back);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(Departamento.this, EliminarDepartamentos.class);
                startActivity(delete);
            }
        });


        addData();
        update();

    }

    /**
     * METODO PARA AGREGAR REGISTROS
     */
    public void addData() {
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertDataDepartamentos(etName.getText().toString(),
                                etCod.getText().toString());

                        if (isInserted = true)

                            Toast.makeText(Departamento.this, "GUARDADO", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Departamento.this, "NO SE GUARDO", Toast.LENGTH_LONG).show();
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
                        boolean isUpdate = myDB.updateDepartamentos(spinnerDeptos.getSelectedItem().toString(), etName.getText().toString(), etCod.getText().toString());
                        if (isUpdate)
                            Toast.makeText(Departamento.this, "Editado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Departamento.this, "Fallo al editar", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerDeptos:

                idDepartamentos = myDB.getAllDepartamentos().get(position).getId();

                nombreDepartamentos = myDB.getAllDepartamentos().get(position).getNombreDepartamentos();
                codigoDepartamentos = myDB.getAllDepartamentos().get(position).getCodigoDepartamentos();

                Toast.makeText(this, "Id Departamentos: " + idDepartamentos + " - Nombre departamento: " + nombreDepartamentos + " - Codigo departamento: " + codigoDepartamentos, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
