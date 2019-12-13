package pt.ulp.se201920_g01_20091502_21704830

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    //
    //Not TODO: CREATION OF TABLES
    //
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL( "CREATE TABLE Utilizador ( " +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "USERNAME TEXT UNIQUE, " +
                "NOME TEXT, " +
                "EMAIL TEXT, " +
                "PASSCODE TEXT " +
                ")"
//            "CREATE TABLE $TABLE_NAME " +
//                    "($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AGE TEXT, $COLUMN_EMAIL TEXT)"
        )
        db.execSQL("CREATE TABLE Veiculo ( " +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "MATRICULA TEXT UNIQUE, " +
                "MARCA TEXT, " +
                "MODELO TEXT, " +
                "ANO INT, " +
                "FOREIGN KEY (ID) REFERENCES Utilizador(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ")"
        )
        db.execSQL("CREATE TABLE Seguro ( " +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "NOME TEXT, " +
                "DATA_INI DATE, " +
                "DATA_FIM DATE, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ")"
        )
        db.execSQL("CREATE TABLE Manut_Prog ( " +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "DATA_D DATE, " +
                "DESCRICAO TEXT, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ")"
        )
        db.execSQL("CREATE TABLE Viagem_Realizada ( " +
                "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "LOCAL_INI TEXT, " +
                "LOCAL_DEST TEXT, " +
                "DATA_D DATE, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador")
        db.execSQL("DROP TABLE IF EXISTS Veiculo")
        db.execSQL("DROP TABLE IF EXISTS Seguro")
        db.execSQL("DROP TABLE IF EXISTS Manut_Prog")
        db.execSQL("DROP TABLE IF EXISTS Viagem_Realizada")
//        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //
    //Not TODO: QUERIES
    //

    fun getGeneralInfo(vehicle_ID: Int): Cursor?{

        //Necessary VEIC: ANO, VEIC: MARCA - Modelo, SEGURO: NOME - DATA_VAL
        val db = this.readableDatabase
        var query= db.rawQuery("SELECT veiculo.MATRICULA, veiculo.MARCA, veiculo.MODELO, veiculo.ANO from veiculo WHERE veiculo.ID=$vehicle_ID", null)
        //query= db.rawQuery("SELECT seguro.NOME, seguro.DATA_FIM from seguro WHERE seguro.ID=(SELECT max(seguro.ID) from seguro WHERE seguro.VEICULO_ID=$vehicle_ID)",null)
        return query
    }

//    fun insertRow(name: String, age:String, email: String) {
//        val values = ContentValues()
//        values.put(COLUMN_NAME, name)
//        values.put(COLUMN_AGE, age)
//        values.put(COLUMN_EMAIL, email)
//
//        val db = this.writableDatabase
//        db.insert(TABLE_NAME, null, values)
//        db.close()
//    }
//
//    fun updateRow(row_id: String, name: String, age:String, email: String) {
//        val values = ContentValues()
//        values.put(COLUMN_NAME, name)
//        values.put(COLUMN_AGE, age)
//        values.put(COLUMN_EMAIL, email)
//
//        val db = this.writableDatabase
//        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(row_id))
//        db.close()
//    }
//
//    fun deleteRow(row_id: String) {
//        val db = this.writableDatabase
//        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(row_id))
//        db.close()
//    }
//
//    fun getAllRow(): Cursor? {
//        val db = this.readableDatabase
//        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
//    }
//
//    fun getLastRow(): Cursor? {
//        val db = this.readableDatabase
//        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=(SELECT max($COLUMN_ID) FROM $TABLE_NAME)", null)
//    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "frota_BD.db"

//        const val TABLE_NAME = "users"
//        const val COLUMN_ID = "id"
//        const val COLUMN_NAME = "name"
//        const val COLUMN_AGE = "age"
//        const val COLUMN_EMAIL = "email"
    }
}