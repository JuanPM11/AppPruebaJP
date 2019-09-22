package com.example.apppruebajp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    DataBaseHelper myDB;

    private static final String TAG = "logcat";

    private static DataBaseHelper sInstance;

    private static final String NOMBRE_BASE_DATOS = "pruebaTelefonos.db";
    private static final int DATABASE_VERSION = 1;

    //Nombre de la tabla
    private static final String TABLA_DEPARTAMENTOS = "departamentos";
    private static final String TABLA_MUNICIPIOS = "municipios";

    private static final String ID_DEPARTAMENTO = "_id";
    private static final String NOMBRE_DEPARTAMENTO = "nombre_departamento";
    private static final String CODIGO_DEPARTAMENTO = "codigo_departamento";

    private static final String ID_MUNICIPIO = "_id";
    private static final String NOMBRE_MUNICIPIO = "nombre_municipio";
    private static final String CODIGO_MUNICIPIO = "codigo_municipio";
    private static final String DEPARTAMENTO_ID = "departamento_id";

    public static synchronized DataBaseHelper getInstance(Context context) {
        // Utilice el contexto de la aplicación, que garantizará que
        // no se filtra accidentalmente el contexto de una actividad.
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    DataBaseHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREAR_TABLA_DEPARTAMENTOS = "CREATE TABLE " + TABLA_DEPARTAMENTOS +
                "(" +
                ID_DEPARTAMENTO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE_DEPARTAMENTO + " TEXT NOT NULL, " +
                CODIGO_DEPARTAMENTO + " TEXT" +
                ")";


        String CREAR_TABLA_MUNICIPIOS = "CREATE TABLE " + TABLA_MUNICIPIOS +
                "(" +
                ID_MUNICIPIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE_MUNICIPIO + " TEXT NOT NULL, " +
                CODIGO_MUNICIPIO + " TEXT, " +
                DEPARTAMENTO_ID + " INTEGER, " +
                " CONSTRAINT fk_departmentos FOREIGN KEY(departamento_id) REFERENCES departamentos(_id)" +
                ")";

        db.execSQL(CREAR_TABLA_DEPARTAMENTOS);
        db.execSQL(CREAR_TABLA_MUNICIPIOS);

        //Inserto en la base de datos,  los nombres de países por única vez
        insertarDepartamentos(db);
        insertarMunicipios(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String tablaDepartamentos = "DROP TABLE IF EXISTS " + TABLA_DEPARTAMENTOS;
            String tablaMunicipios = "DROP TABLE IF EXISTS " + TABLA_MUNICIPIOS;

            db.execSQL(tablaMunicipios);
            db.execSQL(tablaDepartamentos);

            onCreate(db);

        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }

    private void insertarDepartamentos(SQLiteDatabase db) {
        insertarDatos(db, valores("1", "ANTIOQUIA", "4"));
        insertarDatos(db, valores("2", "CUNDINAMARCA", "1"));

    }

    private void insertarMunicipios(SQLiteDatabase db) {
        insertarDatosMun(db, valoresMun("1", "ITAGUI", "4_11", 1));
        insertarDatosMun(db, valoresMun("2", "SOACHA", "1_22", 2));

    }

    private long insertarDatos(SQLiteDatabase db, ContentValues valores) {
        return db.insert(
                DataBaseHelper.TABLA_DEPARTAMENTOS,
                null,
                valores);
    }

    private long insertarDatosMun(SQLiteDatabase db, ContentValues valoresMun) {
        return db.insert(
                DataBaseHelper.TABLA_MUNICIPIOS,
                null,
                valoresMun);
    }


    private ContentValues valores(String id, String nombreDepartamentos, String codigoDepartamentos) {
        // Contenedor de valores
        ContentValues values = new ContentValues();
        // Pares clave-valor
        values.put(DataBaseHelper.ID_DEPARTAMENTO, id);
        values.put(DataBaseHelper.NOMBRE_DEPARTAMENTO, nombreDepartamentos);
        values.put(DataBaseHelper.CODIGO_DEPARTAMENTO, codigoDepartamentos);

        return values;
    }

    private ContentValues valoresMun(String id, String nombreMunicipios, String codigoMunicipios, Integer departamentoId) {
        // Contenedor de valores
        ContentValues valuesMun = new ContentValues();
        // Pares clave-valor
        valuesMun.put(DataBaseHelper.ID_MUNICIPIO, id);
        valuesMun.put(DataBaseHelper.NOMBRE_MUNICIPIO, nombreMunicipios);
        valuesMun.put(DataBaseHelper.CODIGO_MUNICIPIO, codigoMunicipios);
        valuesMun.put(DataBaseHelper.DEPARTAMENTO_ID, departamentoId);
        return valuesMun;
    }


    public List<Departamentos> getAllDepartamentos() {
        List<Departamentos> departamentos = new ArrayList<>();

        String QUERY_SELECCIONAR_DEPARTAMENTO =
                String.format("SELECT %s, %s, %s FROM %s",
                        ID_DEPARTAMENTO,
                        NOMBRE_DEPARTAMENTO,
                        CODIGO_DEPARTAMENTO,
                        TABLA_DEPARTAMENTOS);

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(QUERY_SELECCIONAR_DEPARTAMENTO, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Departamentos nuevoDepartamento = new Departamentos(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2)

                    );
                    departamentos.add(nuevoDepartamento);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error mientras se intenta conseguir los registros de la base de datos");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return departamentos;
    }


    public List<Municipios> getAllMunicipios() {
        List<Municipios> municipios = new ArrayList<>();

        String QUERY_SELECCIONAR_MUNICIPIOS =
                String.format("SELECT %s, %s, %s, %s FROM %s",
                        ID_MUNICIPIO,
                        NOMBRE_MUNICIPIO,
                        CODIGO_MUNICIPIO,
                        DEPARTAMENTO_ID,
                        TABLA_MUNICIPIOS);

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(QUERY_SELECCIONAR_MUNICIPIOS, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Municipios nuevoMunicipio = new Municipios(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)

                    );
                    municipios.add(nuevoMunicipio);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error mientras se intenta conseguir los registros de la base de datos");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return municipios;
    }

    /////////// DEPARTAMENTOS CRUD /////////////////////////

    ///////// AGREGAR DEPARTAMENTOS

    public boolean insertDataDepartamentos(String NOMBRE, String CODIGO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMBRE_DEPARTAMENTO, NOMBRE);
        contentValues.put(CODIGO_DEPARTAMENTO, CODIGO);

        long result = db.insert(TABLA_DEPARTAMENTOS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //////// ELIMINAR DEPARTAMENTOS

    public Integer deleteDepartamentos(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_DEPARTAMENTOS, "_id = ?", new String[]{id});
    }

    ////EDITAR DEPARTAMENTOS

    public boolean updateDepartamentos(String id, String NOMBRE, String CODIGO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_DEPARTAMENTO, id);
        contentValues.put(NOMBRE_DEPARTAMENTO, NOMBRE);
        contentValues.put(CODIGO_DEPARTAMENTO, CODIGO);
        db.update(TABLA_DEPARTAMENTOS, contentValues, "_id = ?", new String[]{id});
        Cursor cursor = db.rawQuery("SELECT _id, codigo_municipio FROM " + TABLA_MUNICIPIOS + " WHERE departamento_id = " + id , null);
        if (cursor.moveToFirst()) {
            do {
                String cMun = cursor.getString(0);
                String codigo = cursor.getString(1).split("_")[1];
                String nCodigo = CODIGO + "_" + codigo;
                ContentValues nValues = new ContentValues();
                nValues.put(CODIGO_MUNICIPIO, nCodigo);
                db.update(TABLA_MUNICIPIOS, nValues, "_id = ?", new String[]{cMun});
            } while (cursor.moveToNext());
        }

        return true;
    }


    ////// MUNICIPIOS CRUD //////////


    //// AGREGAR MUNICIPIOS

    public boolean insertDataMunicipios(String NOMBRE, String CODIGO, String departamento_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMBRE_MUNICIPIO, NOMBRE);
        Cursor cursor = db.rawQuery("SELECT _id, codigo_departamento FROM " + TABLA_DEPARTAMENTOS + " WHERE " + NOMBRE_DEPARTAMENTO + " = " + "'" + departamento_id + "'", null);
        cursor.moveToFirst();
        contentValues.put(DEPARTAMENTO_ID, cursor.getString(0));
        contentValues.put(CODIGO_MUNICIPIO, cursor.getString(1) + "_" + CODIGO);
        long result = db.insert(TABLA_MUNICIPIOS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    ///// BORRAR MUNICIPIOS

    public Integer deleteMunicipios(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_MUNICIPIOS, "_id = ?", new String[]{id});
    }

    ////// EDITAR MUNICIPIOS
    public boolean updateMunicipios(String id, String NOMBRE, String CODIGO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_MUNICIPIO, id);
        contentValues.put(NOMBRE_MUNICIPIO, NOMBRE);
        contentValues.put(CODIGO_MUNICIPIO, CODIGO);
        db.update(TABLA_MUNICIPIOS, contentValues, "_id = ?", new String[]{id});
        return true;
    }


    //////// BUSCAR MUNICIPIOS

    public String[] BuscarDept(String CODIGO) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery(" SELECT * FROM " + TABLA_MUNICIPIOS + " WHERE " + CODIGO_MUNICIPIO + " =  " + "'" + CODIGO + "'", null);
        cursor.moveToFirst();
        String[] array = new String[2];
        array[0] = cursor.getString(1);
        Cursor cursor2 = bd.rawQuery(" SELECT nombre_departamento FROM " + TABLA_DEPARTAMENTOS + " WHERE _id = " + cursor.getString(3), null);
        cursor2.moveToFirst();
        array[1] = cursor2.getString(0);
        return array;
    }

}