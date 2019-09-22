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

    Button Municipios, Departamentos, Buscar;
    //Variables para cargar Spinner desde SQLite
    DataBaseHelper helper;
    Spinner spSpinnerSql, spSpinnerSqlMun ;
    List<String> listaDepartamentosSql, listaMunicipiosSql;
    ArrayAdapter<String> comboAdapterSql, comboAdapterSqlMun;
    String idDepartamentos, nombreDepartamentos, codigoDepartamentos;
    String idMunicipios, nombreMunicipios, codigoMunicipios, departamentosID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Municipios = (Button) findViewById(R.id.btnMun);
        Departamentos = (Button) findViewById(R.id.btnDeptos);
        Buscar = (Button) findViewById(R.id.btnSearch);

        Municipios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Municiopios = new Intent(MainActivity.this, Municipio.class);
                startActivity(Municiopios);
            }
        });

        Departamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Departamentos = new Intent(MainActivity.this, Departamento.class);
                startActivity(Departamentos);
            }
        });

        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Buscar = new Intent(MainActivity.this, Buscar.class);
                startActivity(Buscar);
            }
        });


        //================Datos cargados desde SQLite=====================//
        //Instancio la variable helper a DataBaseHelper.getInstance()
        //Sirve para poder usar los métodos y propiedades SQLite creados anteriormente
        helper = DataBaseHelper.getInstance(this);


        spSpinnerSql = (Spinner) findViewById(R.id.spinner);
        spSpinnerSqlMun = (Spinner) findViewById(R.id.spinnerMun);
        //Implemento el setOnItemSelectedListener: para realizar acciones cuando se seleccionen los ítems
        spSpinnerSql.setOnItemSelectedListener(this);
        spSpinnerSqlMun.setOnItemSelectedListener(this);
        //Convierto la variable List<> en un ArrayList<>()
        listaDepartamentosSql = new ArrayList<>();
        listaMunicipiosSql = new ArrayList<>();
        //Almaceno el tamaño de la lista getAllPaises()
        int sizeListaDepartamentos = helper.getAllDepartamentos().size();
        int sizeListaMunicipios = helper.getAllMunicipios().size();
        //Agrego los nombres de los países obtenidos y lo almaceno en  `listaPaisesSql`
        for (int i = 0; i < sizeListaDepartamentos; i++) {
            listaDepartamentosSql.add(helper.getAllDepartamentos().get(i).getNombreDepartamentos());
        }
        for (int i = 0; i < sizeListaMunicipios; i++) {
            listaMunicipiosSql.add(helper.getAllMunicipios().get(i).getNombreMunicipios());
        }
        //Implemento el adapter con el contexto, layout, listaPaisesSql
        comboAdapterSql = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDepartamentosSql);
        comboAdapterSqlMun = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaMunicipiosSql);
        //Cargo el spinner con los datos
        spSpinnerSql.setAdapter(comboAdapterSql);
        spSpinnerSqlMun.setAdapter(comboAdapterSqlMun);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner:
                //Almaceno el id del país seleccionado
                idDepartamentos = helper.getAllDepartamentos().get(position).getId();
                //Almaceno el nombre del país seleccionado
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
