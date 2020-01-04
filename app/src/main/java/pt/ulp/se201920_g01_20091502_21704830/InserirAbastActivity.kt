package pt.ulp.se201920_g01_20091502_21704830

import android.R.attr.fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import pt.ulp.se201920_g01_20091502_21704830.Fragment.AbastecimentosFragment


class InserirAbastActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserir_abast)

        toolbar = findViewById(R.id.toolbarInsesirMan)
        setSupportActionBar(toolbar)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_btn, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.back_btn -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    fun insertAbastData(view: View?){
        var preferences = this.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        if(findViewById<EditText>(R.id.ins_abast_data).text.toString()!="" && findViewById<EditText>(R.id.ins_abast_local).text.toString()!="" && findViewById<EditText>(R.id.ins_abast_quant).text.toString()!="" && findViewById<EditText>(R.id.ins_abast_val_gast).text.toString()!=""){
            var sentData= DatabaseHelper(this,null).insrtAbast(preferences.getString("ID", "").toString(),
                                                               findViewById<EditText>(R.id.ins_abast_data).text.toString(),
                                                               findViewById<EditText>(R.id.ins_abast_local).text.toString(),
                                                               findViewById<EditText>(R.id.ins_abast_quant).text.toString(),
                                                               findViewById<EditText>(R.id.ins_abast_val_gast).text.toString())
            Toast.makeText(this, "Dados Inseridos", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Preencha todos os espaços", Toast.LENGTH_SHORT).show()
        }
    }

}
