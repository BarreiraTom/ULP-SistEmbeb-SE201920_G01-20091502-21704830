package pt.ulp.se201920_g01_20091502_21704830



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
=======
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
>>>>>>> 743e3831293b3a5389bdd53747b1b83c43a16df6


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

<<<<<<< HEAD
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
=======
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.right_menu) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT)
            }
        }

        return super.onOptionsItemSelected(item)
>>>>>>> 743e3831293b3a5389bdd53747b1b83c43a16df6
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
