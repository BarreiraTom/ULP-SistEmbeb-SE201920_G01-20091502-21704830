package pt.ulp.se201920_g01_20091502_21704830

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testlayout)
    }

    fun testInsert(view: View?){//TODO ERROR LINE !/Caused by: java.lang.ClassCastException: android.text.SpannableStringBuilder cannot be cast to java.lang.String
        var fstName= findViewById<EditText>(R.id.fstNameEdit).text as String
        var scndName= findViewById<EditText>(R.id.scndNameEdit).text as String
        var Age= findViewById<EditText>(R.id.iddEdit).text as String

        Log.println(Log.ASSERT, "FstName", fstName)
        Log.println(Log.ASSERT, "ScndName", scndName)
        Log.println(Log.ASSERT, "Age", Age)
    }
}
