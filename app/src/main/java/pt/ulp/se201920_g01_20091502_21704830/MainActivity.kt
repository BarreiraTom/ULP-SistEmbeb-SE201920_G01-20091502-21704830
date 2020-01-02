package pt.ulp.se201920_g01_20091502_21704830

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        setContentView(R.layout.testlayout)
=======
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.id_toolbar)
        setSupportActionBar(toolbar)

        //Hide status bar

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        ////////////////////////////////////////////////////

        drawerLayout = findViewById(R.id.id_drawer)
        val navView = findViewById<NavigationView>(R.id.id_nav_viewer)



        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_manutencao), drawerLayout)

        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.right_side_menu, menu)
        return true
>>>>>>> Stashed changes
    }

    fun testInsert(view: View?){
        var fstName= findViewById<EditText>(R.id.fstNameEdit).text
        var scndName= findViewById<EditText>(R.id.scndNameEdit).text
        var Age= findViewById<EditText>(R.id.iddEdit).text

        Log.println(Log.ASSERT, "FstName", "$fstName")
        Log.println(Log.ASSERT, "ScndName", "$scndName")
        Log.println(Log.ASSERT, "Age", "$Age")
    }
}
