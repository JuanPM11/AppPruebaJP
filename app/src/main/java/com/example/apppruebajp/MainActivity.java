package com.example.apppruebajp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener   {
    /// Se inicializa los botones
    Button Municipios, Departamentos, Buscar;
    // Se instancia la base de datos
    DataBaseHelper helper;
    // Spinner para cargar los nombres de los departamentos y municipios
    Spinner spSpinnerSql, spSpinnerSqlMun ;
    //Lista para cargar los departamentos y municipios
    List<String> listaDepartamentosSql, listaMunicipiosSql;
    ArrayAdapter<String> comboAdapterSql, comboAdapterSqlMun;
    String idDepartamentos, nombreDepartamentos, codigoDepartamentos;
    String idMunicipios, nombreMunicipios, codigoMunicipios, departamentosID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //// Se obtiene el Id de los botones
        Municipios = (Button) findViewById(R.id.btnMun);
        Departamentos = (Button) findViewById(R.id.btnDeptos);
        Buscar = (Button) findViewById(R.id.btnSearch);

        // Navegación a la clase Municipios
        Municipios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Municiopios = new Intent(MainActivity.this, Municipio.class);
                startActivity(Municiopios);
            }
        });
        // Navegación a la clase Departamentos
        Departamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Departamentos = new Intent(MainActivity.this, Departamento.class);
                startActivity(Departamentos);
            }
        });
        // Navegación a la clase Buscar
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Buscar = new Intent(MainActivity.this, Buscar.class);
                startActivity(Buscar);
            }
        });
        //Se instancia a la base de datos
        helper = DataBaseHelper.getInstance(this);

        //// Se obtiene el Id de los spinner
        spSpinnerSql = (Spinner) findViewById(R.id.spinner);
        spSpinnerSqlMun = (Spinner) findViewById(R.id.spinnerMun);

        // Se obtiene el item seleccionado del spinner
        spSpinnerSql.setOnItemSelectedListener(this);
        spSpinnerSqlMun.setOnItemSelectedListener(this);

        // Array de la lista de departamentos y municipios
        listaDepartamentosSql = new ArrayList<>();
        listaMunicipiosSql = new ArrayList<>();

        int sizeListaDepartamentos = helper.getAllDepartamentos().size();
        int sizeListaMunicipios = helper.getAllMunicipios().size();

        for (int i = 0; i < sizeListaDepartamentos; i++) {
            listaDepartamentosSql.add(helper.getAllDepartamentos().get(i).getNombreDepartamentos());
        }
        for (int i = 0; i < sizeListaMunicipios; i++) {
            listaMunicipiosSql.add(helper.getAllMunicipios().get(i).getNombreMunicipios());
        }

        comboAdapterSql = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDepartamentosSql);
        comboAdapterSqlMun = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaMunicipiosSql);

        spSpinnerSql.setAdapter(comboAdapterSql);
        spSpinnerSqlMun.setAdapter(comboAdapterSqlMun);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner:

                idDepartamentos = helper.getAllDepartamentos().get(position).getId();

                nombreDepartamentos = helper.getAllDepartamentos().get(position).getNombreDepartamentos();
                codigoDepartamentos = helper.getAllDepartamentos().get(position).getCodigoDepartamentos();

                Toast.makeText(this, "Id departamento: " + idDepartamentos + " - Nombre departamento: " + nombreDepartamentos + " - Codigo departamento: " + codigoDepartamentos,Toast.LENGTH_SHORT).show();
                break;
            case R.id.spinnerMun:

                idMunicipios = helper.getAllMunicipios().get(position).getId();
                nombreMunicipios = helper.getAllMunicipios().get(position).getNombreMunicipios();
                codigoMunicipios = helper.getAllMunicipios().get(position).getCodigoMunicipios();
                departamentosID = helper.getAllMunicipios().get(position).getDepartamentoId();

                Toast.makeText(this, "Id municipio: " + idMunicipios + " Nombre municipio:" + nombreMunicipios + " Codigo municipio: " + codigoMunicipios + " Codigo del departamento " + departamentosID,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
