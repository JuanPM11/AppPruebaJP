package com.example.apppruebajp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    DataBaseHelper myDB;

    private static final String TAG = "logcat";
    /// Instacia de la base de datos
    private static DataBaseHelper sInstance;
    /// Nombre de la base de datos
    private static final String NOMBRE_BASE_DATOS = "pruebaTelefonos.db";
    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    /////Creación de las tablas

    //Nombre de la tabla Departamentos
    private static final String TABLA_DEPARTAMENTOS = "departamentos";
    //Nombre de la tabla Municipios
    private static final String TABLA_MUNICIPIOS = "municipios";
    //Creación de las columnas de la tabla Departamentos
    private static final String ID_DEPARTAMENTO = "_id";
    private static final String NOMBRE_DEPARTAMENTO = "nombre_departamento";
    private static final String CODIGO_DEPARTAMENTO = "codigo_departamento";
    //Creación de las columnas de la tabla Municipios
    private static final String ID_MUNICIPIO = "_id";
    private static final String NOMBRE_MUNICIPIO = "nombre_municipio";
    private static final String CODIGO_MUNICIPIO = "codigo_municipio";
    private static final String DEPARTAMENTO_ID = "departamento_id";

    public static synchronized DataBaseHelper getInstance(Context context) {

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

    /**
     * Creación de las tablas
     * @param db Base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Query de la creación de la tabla Departamentos
        String CREAR_TABLA_DEPARTAMENTOS = "CREATE TABLE " + TABLA_DEPARTAMENTOS +
                "(" +
                ID_DEPARTAMENTO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE_DEPARTAMENTO + " TEXT NOT NULL, " +
                CODIGO_DEPARTAMENTO + " TEXT" +
                ")";

        //Query de la creción de la tabla Municipios
        String CREAR_TABLA_MUNICIPIOS = "CREATE TABLE " + TABLA_MUNICIPIOS +
                "(" +
                ID_MUNICIPIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE_MUNICIPIO + " TEXT NOT NULL, " +
                CODIGO_MUNICIPIO + " TEXT, " +
                DEPARTAMENTO_ID + " INTEGER, " +
                " CONSTRAINT fk_departmentos FOREIGN KEY(departamento_id) REFERENCES departamentos(_id)" +
                ")";
        //Se ejecuta el Query
        db.execSQL(CREAR_TABLA_DEPARTAMENTOS);
        db.execSQL(CREAR_TABLA_MUNICIPIOS);

        insertarDepartamentos(db);
        insertarMunicipios(db);
    }

    /**
     * Metodo para actualizar la base de datos cada vez que se ejecuta la aplicación
     * @param db Base de datos
     * @param oldVersion Versión anterior
     * @param newVersion Nueva versión de la Base de datos
     */
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

    /**
     * Actualiza la base de datos
     * @param db Base de datos
     * @param oldVersion Versión anterior de la Base de datos
     * @param newVersion Versión nueva de la Base de datos
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }

    /**
     * Metodo para insertar los registros en la tabla de Departamentos
     * @param db Base de datos
     */
    private void insertarDepartamentos(SQLiteDatabase db) {
        insertarDatos(db, valores("1", "ANTIOQUIA", "4"));
        insertarDatos(db, valores("2", "CUNDINAMARCA", "1"));

    }

    /**
     * Metodo para insertar los registros en la tabla de Municipios
     * @param db Base de datos
     */
    private void insertarMunicipios(SQLiteDatabase db) {
        insertarDatosMun(db, valoresMun("1", "ITAGUI", "4_11", 1));
        insertarDatosMun(db, valoresMun("2", "SOACHA", "1_22", 2));

    }

    /**
     * Metodo para insertar los datos a la tabla de Departamentos
     * @param db Base de datos
     * @param valores Datos que se insertan a la tabla
     * @return
     */
    private long insertarDatos(SQLiteDatabase db, ContentValues valores) {
        return db.insert(
                DataBaseHelper.TABLA_DEPARTAMENTOS,
                null,
                valores);
    }

    /**
     * Metodo para insertar los datos a la tabla de Municipios
     * @param db Base de datos
     * @param valoresMun Datos que se insertan a la tabla
     * @return
     */
    private long insertarDatosMun(SQLiteDatabase db, ContentValues valoresMun) {
        return db.insert(
                DataBaseHelper.TABLA_MUNICIPIOS,
                null,
                valoresMun);
    }

    /**
     * Metodo para ejecutar los datos por parámentro para la tabla de Departamentos
     * @param id Id del Departamento
     * @param nombreDepartamentos Nombre del departamento
     * @param codigoDepartamentos Codigo del departamento
     * @return
     */
    private ContentValues valores(String id, String nombreDepartamentos, String codigoDepartamentos) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_DEPARTAMENTO, id);
        values.put(DataBaseHelper.NOMBRE_DEPARTAMENTO, nombreDepartamentos);
        values.put(DataBaseHelper.CODIGO_DEPARTAMENTO, codigoDepartamentos);

        return values;
    }

    /**
     * Metodo para ejecutar los datos por parámentro para la tabla de Municipios
     * @param id Id del Municipio
     * @param nombreMunicipios Nombre del Municipio
     * @param codigoMunicipios Codigo del Municipio
     * @param departamentoId Id del Departamento
     * @return
     */
    private ContentValues valoresMun(String id, String nombreMunicipios, String codigoMunicipios, Integer departamentoId) {

        ContentValues valuesMun = new ContentValues();

        valuesMun.put(DataBaseHelper.ID_MUNICIPIO, id);
        valuesMun.put(DataBaseHelper.NOMBRE_MUNICIPIO, nombreMunicipios);
        valuesMun.put(DataBaseHelper.CODIGO_MUNICIPIO, codigoMunicipios);
        valuesMun.put(DataBaseHelper.DEPARTAMENTO_ID, departamentoId);
        return valuesMun;
    }

    /**
     * Metodo para obtener en una Lista todos los Departamentos
     * @return departamentos
     */
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

    /**
     * Metodo para obtener en una Lista todos los Municipios
     * @return municipios
     */
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

    /**
     * Metodo que sirve para ingresar datos a la tabla Departamentos
     * @param NOMBRE Nombre del Departamento
     * @param CODIGO Codigo del Departamento
     * @return Verdadero si la inserción se ejecuta correctamente o falso si existe algún problema
     */
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

    /**
     * Método que sirve para eliminar un registro de la tabla Departamentos
     * @param id id del Departamentos
     * @return retorna el query para eliminar un registro de la tabla Departamentos
     */
    public Integer deleteDepartamentos(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_DEPARTAMENTOS, "_id = ?", new String[]{id});
    }

    ////EDITAR DEPARTAMENTOS

    /**
     * Método que sirve para actualizar los Departamentos
     * @param NOMBRE Nombre del Departamento
     * @param CODIGO Codigo o indicativo del Departamento
     * @return Verdadero si actualiza el Departamento o falso si ocurre algún error
     */
    public boolean updateDepartamentos(String nombreDept, String NOMBRE, String CODIGO) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery(" SELECT _id FROM " + TABLA_DEPARTAMENTOS + " WHERE nombre_departamento = '" + nombreDept + "'", null);
        cursor.moveToFirst();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_DEPARTAMENTO, cursor.getString(0));
        contentValues.put(NOMBRE_DEPARTAMENTO, NOMBRE);
        contentValues.put(CODIGO_DEPARTAMENTO, CODIGO);
        db.update(TABLA_DEPARTAMENTOS, contentValues, "_id = ?", new String[]{cursor.getString(0)});
        Cursor cursor2 = db.rawQuery("SELECT _id, codigo_municipio FROM " + TABLA_MUNICIPIOS + " WHERE departamento_id = " + cursor.getString(0) , null);
        if (cursor2.moveToFirst()) {
            do {
                String cMun = cursor2.getString(0);
                String codigo = cursor2.getString(1).split("_")[1];
                String nCodigo = CODIGO + "_" + codigo;
                ContentValues nValues = new ContentValues();
                nValues.put(CODIGO_MUNICIPIO, nCodigo);
                db.update(TABLA_MUNICIPIOS, nValues, "_id = ?", new String[]{cMun});
            } while (cursor2.moveToNext());
        }

        return true;
    }


    /////////////////////// MUNICIPIOS CRUD /////////////////////////////////


    //// AGREGAR MUNICIPIOS

    /**
     * Metodo que sirve para agregar Municipios
     * @param NOMBRE Nombre del Municipio
     * @param CODIGO Código del Municipio
     * @param departamento_id Id del departamento para relacionarlo segun el Municipio
     * @return Verdadero si guarda correctamente o falso si ocurre algún error
     */
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

    /**
     * Método que sirve para eliminar un registro de la tabla Departamentos
     * @param id id del registro de la tabla Departamentos
     * @return retorna el query para eliminar un registro de la tabla Departamentos
     */
    public Integer deleteMunicipios(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_MUNICIPIOS, "_id = ?", new String[]{id});
    }

    ////// EDITAR MUNICIPIOS

    /**
     * Método que sirve para actualizar los Departamentos
     * @param NOMBRE nombre del registro de la tabla Departamentos
     * @param CODIGO codigo del registro de la tabla Departamentos
     * @return Verdadero si actualiza de forma correcta y falso si ocurre algún error
     */
    public boolean updateMunicipios(String NombreMun, String NOMBRE, String CODIGO) {
        SQLiteDatabase bd = getReadableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = bd.rawQuery(" SELECT _id FROM " + TABLA_MUNICIPIOS + " WHERE nombre_municipio = '" + NombreMun + "'", null);
        cursor.moveToFirst();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_MUNICIPIO, cursor.getString(0));
        contentValues.put(NOMBRE_MUNICIPIO, NOMBRE);
        contentValues.put(CODIGO_MUNICIPIO, CODIGO);
        db.update(TABLA_MUNICIPIOS, contentValues, "_id = ?", new String[]{cursor.getString(0)});
        return true;
    }


    //////// BUSCAR MUNICIPIOS

    /**
     * Método para Buscar los departamentos mediante Query SQL
     * @param CODIGO codigo del Municipio
     * @return array
     */
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