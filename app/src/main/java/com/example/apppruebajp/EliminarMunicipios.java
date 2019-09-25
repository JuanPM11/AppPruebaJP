package com.example.apppruebajp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EliminarMunicipios extends AppCompatActivity {

    Button back, delete;
    EditText etIdMun;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_municipios);

        myDB = new DataBaseHelper(getApplicationContext());
        myDB = new DataBaseHelper(this);
        delete = (Button) findViewById(R.id.btnDelete);
        back = (Button) findViewById(R.id.back);
        etIdMun = (EditText) findViewById(R.id.etIdMun);
        delete();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(EliminarMunicipios.this, Municipio.class);
                startActivity(Back);
            }
        });
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
                            Toast.makeText(EliminarMunicipios.this, "Eliminado correctamente", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EliminarMunicipios.this, "Fallo al Eliminar", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
