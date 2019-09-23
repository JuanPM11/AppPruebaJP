package com.example.apppruebajp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Departamento extends AppCompatActivity {
    /// Se inicializa los botones, cajas de texto y base de datos
    DataBaseHelper myDB;
    Button Back, save, delete, edit;
    EditText etName, etCod, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento);

        //// Se obtiene el Id de los botones, cajas de texto y base de datos
        id = (EditText) findViewById(R.id.Id);
        etName = (EditText) findViewById(R.id.etName);
        etCod = (EditText) findViewById(R.id.etCod);


        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);


        save = (Button) findViewById(R.id.btnSave);
        edit = (Button) findViewById(R.id.btnEdit);
        delete = (Button) findViewById(R.id.btnDelete);
        Back = (Button) findViewById(R.id.back);

        // Acción de regresar con el Botón Back
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(Departamento.this, MainActivity.class);
                startActivity(Back);
            }
        });


        addData();
        update();
        delete();
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
     * METODO PARA ELIMINAR  REGISTROS
     */
    public void delete() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteDepartamentos(id.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(Departamento.this, "Eliminado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Departamento.this, "Fallo al Eliminar", Toast.LENGTH_LONG).show();
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
                        boolean isUpdate = myDB.updateDepartamentos(id.getText().toString(), etName.getText().toString(), etCod.getText().toString());
                        if (isUpdate)
                            Toast.makeText(Departamento.this, "Editado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Departamento.this, "Fallo al editar", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
