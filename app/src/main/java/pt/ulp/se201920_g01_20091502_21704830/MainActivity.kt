package pt.ulp.se201920_g01_20091502_21704830

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.*
import pt.ulp.se201920_g01_20091502_21704830.Fragment.HomeFragment
import pt.ulp.se201920_g01_20091502_21704830.Fragment.LoginFragment
import pt.ulp.se201920_g01_20091502_21704830.Fragment.ManutencaoFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    LoginFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener,
    ManutencaoFragment.OnFragmentInteractionListener {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    lateinit var loginFragment: LoginFragment
    lateinit var homeFragment: HomeFragment
    lateinit var manutencaoFragment: ManutencaoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Hide status bar

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        ////////////////////////////////////////////////////

        toolbar = findViewById(R.id.id_toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.id_drawer)
        navView = findViewById(R.id.id_nav_viewer)


        //var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)

        loginFragment = LoginFragment.newInstance()
        homeFragment = HomeFragment.newInstance("Parametro 1", "Parametro 2")
        manutencaoFragment = ManutencaoFragment.newInstance("Podemos passar valores por aqui", "")

        supportFragmentManager.beginTransaction().replace(R.id.id_fragment_container, loginFragment).commit()

        navView.setNavigationItemSelectedListener(object :
                                                      NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {


                when (item.itemId) {
                    R.id.login -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.id_fragment_container, loginFragment)
                            .addToBackStack(loginFragment.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                        Toast.makeText(this@MainActivity, "Login clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.manutencao -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.id_fragment_container, manutencaoFragment)
                            .addToBackStack(manutencaoFragment.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                        Toast.makeText(this@MainActivity, "Login clicked", Toast.LENGTH_SHORT)
                            .show()
                        Toast.makeText(this@MainActivity, "Manutenção clicked", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.id_fragment_container, homeFragment)
                            .addToBackStack(homeFragment.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                        Toast.makeText(this@MainActivity, "Login clicked", Toast.LENGTH_SHORT)
                            .show()
                        Toast.makeText(this@MainActivity, "Home clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

        })
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("Implemented Interface", "Fragment changed")
    }

}
