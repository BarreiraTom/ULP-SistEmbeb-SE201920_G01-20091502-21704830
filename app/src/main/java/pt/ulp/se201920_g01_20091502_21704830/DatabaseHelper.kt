package pt.ulp.se201920_g01_20091502_21704830

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.reflect.typeOf

class DatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    val PreferencesFile= PrefFileHelper()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Empresa(" +
                "ID INTEGER PRIMARY KEY," +
                "USERNAME TEXT, " +
                "PASSCODE TEXT " +
                ");"
        )
        db.execSQL( "CREATE TABLE IF NOT EXISTS Utilizador ( " +
                "ID INTEGER PRIMARY KEY, " +
                "USERNAME TEXT UNIQUE, " +
                "NOME TEXT, " +
                "EMAIL TEXT, " +
                "PASSCODE TEXT, " +
                "EMPRESA_ID INTEGER, " +
                "FOREIGN KEY (EMPRESA_ID) REFERENCES Empresa(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ");"
        )
        db.execSQL("CREATE TABLE IF NOT EXISTS Veiculo ( " +
                "ID INTEGER PRIMARY KEY, " +
                "USERID INTEGER, " +
                "MATRICULA TEXT UNIQUE, " +
                "FOTO INTEGER, " +
                "MARCA TEXT, " +
                "MODELO TEXT, " +
                "ANO INT, " +
                "FOREIGN KEY (USERID) REFERENCES Utilizador(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ");"
        )
        db.execSQL("CREATE TABLE IF NOT EXISTS Seguro ( " +
                "ID INTEGER PRIMARY KEY, " +
                "NOME TEXT, " +
                "DATA_INI DATE, " +
                "DATA_FIM DATE, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ");"
        )
        db.execSQL("CREATE TABLE IF NOT EXISTS Manut_Prog ( " +
                "ID INTEGER PRIMARY KEY, " +
                "NOME_MEC TEXT, " +
                "TIPO_REPAR TEXT, " +
                "DATA_D DATE, " +
                "DESCRICAO TEXT, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ");"
        )
        db.execSQL("CREATE TABLE IF NOT EXISTS Viagem_Realizada ( " +
                "ID INTEGER PRIMARY KEY, " +
                "LOCAL_INI TEXT, " +
                "LOCAL_DEST TEXT, " +
                "DATA_D DATE, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ");"
        )
        db.execSQL("CREATE TABLE IF NOT EXISTS Abast (" +
                "ID INTEGER PRIMARY KEY, " +
                "DATA_A DATE, " +
                "LOCAL TEXT, " +
                "QUANT FLOAT, " +
                "VALOR_GAST FLOAT, " +
                "VEICULO_ID INTEGER, " +
                "FOREIGN KEY (VEICULO_ID) REFERENCES Veiculo(ID) " +
                "ON UPDATE RESTRICT " +
                "ON DELETE RESTRICT " +
                ");"
        )

        //INSERT FIRST LINES IN DB
        db.execSQL("INSERT INTO empresa(ID, USERNAME, PASSCODE) VALUES (12312, 'TransporTom', 'qwerty1');")
        db.execSQL("INSERT INTO empresa(ID, USERNAME, PASSCODE) VALUES (12313, 'JorgExpeditions', 'qwerty2');")

        db.execSQL("INSERT INTO utilizador(USERNAME, NOME, EMAIL, PASSCODE, EMPRESA_ID) VALUES ('tomas','Tomás Barreira','tomas@teste.com','qwerty',12312);")
        db.execSQL("INSERT INTO utilizador(USERNAME, NOME, EMAIL, PASSCODE, EMPRESA_ID) VALUES ('jorge','Jorge Gonçalves','jorge@teste.net','123456789',12312);")
        db.execSQL("INSERT INTO utilizador(USERNAME, NOME, EMAIL, PASSCODE, EMPRESA_ID) VALUES ('tiago','Tiago Santos','tiago@teste.vski','asdfg',12313);")
        db.execSQL("INSERT INTO utilizador(USERNAME, NOME, EMAIL, PASSCODE, EMPRESA_ID) VALUES ('miguel','Miguel Pinheiro','miguel@teste.brr','zxcvbnm',12313);")

        db.execSQL("INSERT INTO veiculo(USERID, MATRICULA, FOTO, MARCA, MODELO, ANO) VALUES (1, 'AD-43-12', 2131165278, 'Monster Truck', 'BIG', 2020);")
        db.execSQL("INSERT INTO veiculo(USERID, MATRICULA, FOTO, MARCA, MODELO, ANO) VALUES (3, 'AS-98-SD', 2131165279, 'Renault', 'Clio', 2004);")
        db.execSQL("INSERT INTO veiculo(USERID, MATRICULA, FOTO, MARCA, MODELO, ANO) VALUES (2, '23-TG-43', 2131165277, 'Carrinho de Choque', 'Smol', 1990);")
        db.execSQL("INSERT INTO veiculo(USERID, MATRICULA, FOTO, MARCA, MODELO, ANO) VALUES (4, '90-SD-AS', 2131165309, 'KIA', 'AIK', 2009);")

        db.execSQL("INSERT INTO seguro(NOME, DATA_INI, DATA_FIM, VEICULO_ID) VALUES ('Seguranz', '2010-10-20', '2019-12-31', 1);")
        db.execSQL("INSERT INTO seguro(NOME, DATA_INI, DATA_FIM, VEICULO_ID) VALUES ('Liberty', '2019-01-19', '2019-10-29', 2);")
        db.execSQL("INSERT INTO seguro(NOME, DATA_INI, DATA_FIM, VEICULO_ID) VALUES ('Mariachi', '2019-11-02', '2020-03-10', 3);")
        db.execSQL("INSERT INTO seguro(NOME, DATA_INI, DATA_FIM, VEICULO_ID) VALUES ('Liberty', '2019-10-30', '2020-01-02', 2);")
        db.execSQL("INSERT INTO seguro(NOME, DATA_INI, DATA_FIM, VEICULO_ID) VALUES ('Marthavil', '2019-07-07', '2019-12-20', 4);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Seguro")
        db.execSQL("DROP TABLE IF EXISTS Manut_Prog")
        db.execSQL("DROP TABLE IF EXISTS Viagem_Realizada")
        db.execSQL("DROP TABLE IF EXISTS Abast")
        db.execSQL("DROP TABLE IF EXISTS Veiculo")
        db.execSQL("DROP TABLE IF EXISTS Utilizador")
        db.execSQL("DROP TABLE IF EXISTS Empresa")

        onCreate(db)
    }

    //
    //Not TODO: QUERIES
    //

//    fun String.encrypt(password: String): String {
//        val secretKeySpec = SecretKeySpec(password.toByteArray(), "AES")
//        val iv = ByteArray(16)
//        val charArray = password.toCharArray()
//        for (i in 0 until charArray.size){
//            iv[i] = charArray[i].toByte()
//        }
//        val ivParameterSpec = IvParameterSpec(iv)
//
//        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
//
//        val encryptedValue = cipher.doFinal(this.toByteArray())
//        return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
//    }
//
//    fun String.decrypt(password: String): String {
//        val secretKeySpec = SecretKeySpec(password.toByteArray(), "AES")
//        val iv = ByteArray(16)
//        val charArray = password.toCharArray()
//        for (i in 0 until charArray.size){
//            iv[i] = charArray[i].toByte()
//        }
//        val ivParameterSpec = IvParameterSpec(iv)
//
//        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
//
//        val decryptedByteValue = cipher.doFinal(Base64.decode(this, Base64.DEFAULT))
//        return String(decryptedByteValue)
//    }



    fun logInApp(user: String, pass: String): Int{
        val db = this.readableDatabase
        var qry: Cursor
        if (user.isNotEmpty() and pass.isNotEmpty()){

            qry= db.rawQuery("SELECT ID FROM utilizador WHERE USERNAME='$user' and PASSCODE='$pass'", null)

//            if (user.isDigitsOnly()){
//                qry= db.rawQuery("SELECT * FROM empresa WHERE ID='$user' and PASSCODE='$pass'", null)
//            }else if(!user.isDigitsOnly()){ //TODO CHECK SYNTAX
//                qry= db.rawQuery("SELECT * FROM utilizador WHERE USERNAME='$user' and PASSCODE='$pass'", null)
//            }else{
//                qry=null as Cursor
//            }
            qry!!.moveToFirst()
            Log.println(Log.ASSERT,"Cursor",qry.toString())

            if(qry!=null && qry.getCount()>0){
                var userId:String= qry?.getString(qry.getColumnIndex("ID"))!!
                //PreferencesFile.SaveLogInCreds("tomas","qwerty","1")   TODO [ALTERADO PARA O LOGINACTIVITY]
                qry.close()
                return userId.toInt() // LOGIN SUCCESSFUL
            }else{
                qry.close()
                return 0 //LOGIN INCORRECT
            }
        }else{
            return -1 //LOGIN INPUTS EMPTY
        }
    }

    fun generalInfo(userId:String): Cursor?{
        val db = this.readableDatabase
        //var userId = PreferencesFile.getID()
        var qry = db.rawQuery("SELECT utilizador.NOME, utilizador.EMAIL, empresa.USERNAME, veiculo.MATRICULA, veiculo.MARCA, veiculo.FOTO, veiculo.MODELO, veiculo.ANO, seguro.NOME as NOME_SEG, seguro.DATA_FIM from veiculo " +
                                      "LEFT JOIN utilizador ON utilizador.ID=veiculo.USERID " +
                                      "LEFT JOIN empresa ON utilizador.EMPRESA_ID=empresa.ID " +
                                      "LEFT JOIN seguro ON veiculo.ID=seguro.VEICULO_ID " +
                                      "WHERE veiculo.USERID=$userId and seguro.ID=(SELECT max(seguro.ID) from seguro WHERE seguro.VEICULO_ID=(SELECT veiculo.ID FROM veiculo WHERE veiculo.USERID=$userId))",null)
        qry!!.moveToFirst()
        return qry
    }


    fun getManutGen(userId: String): Cursor?{
        val db= this.readableDatabase
        var qry = db.rawQuery("SELECT manut_prog.ID, manut_prog.NOME_MEC, manut_prog.TIPO_REPAR, manut_prog.DATA_D, manut_prog.DESCRICAO, manut_prog.VEICULO_ID FROM manut_prog, veiculo " +
                                      "WHERE manut_prog.VEICULO_ID=veiculo.ID AND veiculo.USERID=$userId",null)
        qry!!.moveToFirst()
        return qry
    }

    fun getSegGen(userId: String): Cursor?{
        val db= this.readableDatabase
        var qry = db.rawQuery("SELECT seguro.ID, seguro.NOME, seguro.DATA_INI, seguro.DATA_FIM, seguro.VEICULO_ID FROM seguro, veiculo " +
                                      "WHERE seguro.VEICULO_ID=veiculo.ID AND veiculo.USERID=$userId",null)
        qry!!.moveToFirst()
        return qry
    }

    fun getAbastGen(userId: String): Cursor?{
        val db= this.readableDatabase
        var qry = db.rawQuery("SELECT abast.ID, abast.DATA_A, abast.LOCAL, abast.QUANT, abast.VALOR_GAST, abast.VEICULO_ID FROM abast, veiculo " +
                                      "WHERE abast.VEICULO_ID=veiculo.ID AND veiculo.USERID=$userId",null)
        qry!!.moveToFirst()
        return qry
    }

    fun getViagGen(userId: String): Cursor?{
        val db= this.readableDatabase
        var qry = db.rawQuery("SELECT viagem_realizada.ID, viagem_realizada.LOCAL_INI, viagem_realizada.LOCAL_DEST, viagem_realizada.DATA_D, viagem_realizada.VEICULO_ID FROM viagem_realizada, veiculo " +
                                      "WHERE viagem_realizada.VEICULO_ID=veiculo.ID AND veiculo.USERID=$userId",null)
        qry!!.moveToFirst()
        return qry
    }

    fun insrtAbast(userId:String, data:String, local:String, quant:String, valor_gast:String){
        val db= this.readableDatabase
        var qry= db.rawQuery("SELECT veiculo.ID FROM veiculo WHERE veiculo.USERID=$userId",null)
        qry!!.moveToFirst()

        var veicID=qry?.getString(qry.getColumnIndex("ID"))!!

        val values = ContentValues()
        values.put("VEICULO_ID",veicID)
        values.put("DATA_A",data)
        values.put("LOCAL",local)
        values.put("QUANT",quant)
        values.put("VALOR_GAST",valor_gast)
        var result= db.insert("abast", null, values)
        Log.println(Log.ASSERT, "resultInsert","$result")
        db.close()
    }

    fun insrtViag(userId:String, origem:String, destino:String, data:String){
        val db= this.readableDatabase
        var qry= db.rawQuery("SELECT veiculo.ID FROM veiculo WHERE veiculo.USERID=$userId",null)
        qry!!.moveToFirst()

        var veicID=qry?.getString(qry.getColumnIndex("ID"))!!

        val values = ContentValues()
        values.put("VEICULO_ID",veicID)
        values.put("LOCAL_INI",origem)
        values.put("LOCAL_DEST",destino)
        values.put("DATA_D",data)
        var result= db.insert("viagem_realizada", null, values)
        Log.println(Log.ASSERT, "resultInsert","$result")
        db.close()
    }

    fun insrtSegur(userId: String, nome_segur: String, data_ini: String, data_fim: String){
        val db= this.readableDatabase
        var qry= db.rawQuery("SELECT veiculo.ID FROM veiculo WHERE veiculo.USERID=$userId",null)
        qry!!.moveToFirst()

        var veicID=qry?.getString(qry.getColumnIndex("ID"))!!

        val values = ContentValues()
        values.put("VEICULO_ID",veicID)
        values.put("NOME",nome_segur)
        values.put("DATA_INI",data_ini)
        values.put("DATA_FIM",data_fim)
        var result= db.insert("seguro", null, values)
        Log.println(Log.ASSERT, "resultInsert","$result")
        db.close()
    }

    fun insrtManut(userId:String, nome_mec:String, tipo_repar:String, data:String, desc:String){
        val db= this.readableDatabase
        var qry= db.rawQuery("SELECT veiculo.ID FROM veiculo WHERE veiculo.USERID=$userId",null)
        qry!!.moveToFirst()

        var veicID=qry?.getString(qry.getColumnIndex("ID"))!!

        val values = ContentValues()
        values.put("VEICULO_ID",veicID)
        values.put("NOME_MEC",nome_mec)
        values.put("TIPO_REPAR",tipo_repar)
        values.put("DATA_D",data)
        values.put("DESCRICAO",desc)
        var result= db.insert("manut_prog", null, values)
        Log.println(Log.ASSERT, "resultInsert","$result")
        db.close()
    }

    fun getNotifSeg(userId: String):Cursor?{

        val db= this.readableDatabase
        var qry = db.rawQuery("SELECT seguro.NOME, seguro.DATA_FIM FROM seguro, veiculo WHERE DATA_FIM>=(SELECT CURRENT_DATE) AND DATA_FIM<=(SELECT date((SELECT CURRENT_DATE),'+1 month')) AND seguro.VEICULO_ID=veiculo.ID and veiculo.USERID=$userId",null)
        qry!!.moveToFirst()
        return qry
    }


    companion object {
        const val DATABASE_VERSION = 15
        const val DATABASE_NAME = "frotaBD.db"
    }
}