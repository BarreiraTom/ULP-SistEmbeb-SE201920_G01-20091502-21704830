package pt.ulp.se201920_g01_20091502_21704830



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(){

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration


    val dbHandler = DatabaseHelper(this, null)
    var dataList = ArrayList<HashMap<String, String>>()

    lateinit var nameEditText:EditText
    lateinit var ageEditText:EditText
    lateinit var emailEditText:EditText
    lateinit var modifyId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.id_toolbar)
        setSupportActionBar(toolbar)

        //Hide status bar

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        ////////////////////////////////////////////////////

        drawerLayout = findViewById(R.id.id_drawer)
        val navView = findViewById<NavigationView>(R.id.id_nav_viewer)



        //var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)

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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.right_menu) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun LogOut_Act(view:View?){
        if(PrefFileHelper().EffectLogOut()=="OK"){
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "LogOut Incomplete", Toast.LENGTH_SHORT).show()
        }
    }

}
