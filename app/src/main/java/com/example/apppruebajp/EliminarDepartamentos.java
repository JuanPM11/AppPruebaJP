package com.example.apppruebajp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarDepartamentos extends AppCompatActivity {

    DataBaseHelper myDB;
    Button btnEliminar, back;
    EditText id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_departamentos);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        back = (Button)findViewById(R.id.back);
        id = (EditText)findViewById(R.id.idDepartamento);
        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(EliminarDepartamentos.this, Departamento.class);
                startActivity(Back);
            }
        });


        delete();
    }
    /**
     * METODO PARA ELIMINAR  REGISTROS
     */
    public void delete() {
        btnEliminar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteDepartamentos(id.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(EliminarDepartamentos.this, "Eliminado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EliminarDepartamentos.this, "Fallo al Eliminar", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
