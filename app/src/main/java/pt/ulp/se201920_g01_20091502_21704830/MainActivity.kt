package pt.ulp.se201920_g01_20091502_21704830

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val dbHandler = DatabaseHelper(this, null)
    var dataList = ArrayList<HashMap<String, String>>()

    lateinit var nameEditText:EditText
    lateinit var ageEditText:EditText
    lateinit var emailEditText:EditText
    lateinit var modifyId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testlayout)
    }

//    fun testInsert(view: View?){
//        var fstName= findViewById<EditText>(R.id.fstNameEdit).text
//        var scndName= findViewById<EditText>(R.id.scndNameEdit).text
//        var Age= findViewById<EditText>(R.id.iddEdit).text
//
//        Log.println(Log.ASSERT, "FstName", "$fstName")
//        Log.println(Log.ASSERT, "ScndName", "$scndName")
//        Log.println(Log.ASSERT, "Age", "$Age")
//
//        DBHandler.insertRow(fstName as String, Age as String, scndName as String)
//    }
//
    fun testgetLast(view: View?){

        //var cursor= dbHandler.getLastRow()
        //cursor!!.moveToFirst()

        //Log.println(Log.ASSERT, "CURSORNAME", "${cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))}" )
        //Log.println(Log.ASSERT, "CURSOREMAIL", "${cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL))}" )
        //Log.println(Log.ASSERT, "CURSORAGE", "${cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE))}" )
//        var id= cursor?.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
//        var name= cursor?.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))
//        var email= cursor?.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL))
//        var age= cursor?.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE))
//
//        Log.println(Log.ASSERT, "GetID", "$id")
//        Log.println(Log.ASSERT, "GetFstName", "$name")
//        Log.println(Log.ASSERT, "GetScndName", "$email")
//        Log.println(Log.ASSERT, "GetAge", "$age")
    }

    fun add(v:View){
        val name = findViewById<EditText>(R.id.fstNameEdit).text.toString()
        val age = findViewById<EditText>(R.id.iddEdit).text.toString()
        val email = findViewById<EditText>(R.id.scndNameEdit).text.toString()
        //dbHandler.insertRow(name, age, email)
        Toast.makeText(this, "Data Addeded", Toast.LENGTH_SHORT).show()
    }
}
