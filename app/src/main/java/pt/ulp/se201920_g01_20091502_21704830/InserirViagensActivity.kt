package pt.ulp.se201920_g01_20091502_21704830

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import pt.ulp.se201920_g01_20091502_21704830.Fragment.AbastecimentosFragment
import pt.ulp.se201920_g01_20091502_21704830.Fragment.ViagensFragment

class InserirViagensActivity : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserir_viagens)

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

    fun insertViagData(view: View?){
        var preferences = this.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        if(findViewById<EditText>(R.id.ins_viag_origem).text.toString()!="" && findViewById<EditText>(R.id.ins_viag_dest).text.toString()!="" && findViewById<EditText>(R.id.ins_viag_data).text.toString()!=""){
            var sentData= DatabaseHelper(this,null).insrtViag(preferences.getString("ID", "").toString(),
                                                               findViewById<EditText>(R.id.ins_viag_origem).text.toString(),
                                                               findViewById<EditText>(R.id.ins_viag_dest).text.toString(),
                                                               findViewById<EditText>(R.id.ins_viag_data).text.toString())
            Toast.makeText(this, "Dados Inseridos", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Preencha todos os espaços", Toast.LENGTH_SHORT).show()
        }
    }

}
