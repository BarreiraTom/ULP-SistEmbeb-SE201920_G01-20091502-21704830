package pt.ulp.se201920_g01_20091502_21704830

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val dbHandler= DatabaseHelper(this,null)

        var preferences = getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        var usr= preferences.getString("Username", "").toString()
        var pass= preferences.getString("AccessToken", "").toString()

        if(usr!="" && pass!=""){
            var log= dbHandler.logInApp(usr,pass)

            if(log>=1){             //AUTO LOGIN SUCCESSFUL
                Toast.makeText(this, "Auto Login Successful", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else if(log==0){       //AUTO LOGIN UNSUCCESSFUL
                Toast.makeText(this, "Auto Login Unsuccessful", Toast.LENGTH_SHORT).show()
            }else{                  //AUTO LOGIN NOT FOUND
                Toast.makeText(this, "Auto Login Credentials\nwere not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun makeLogin(view: View) {
        val dbHandler= DatabaseHelper(this,null)
        var user =findViewById<EditText>(R.id.editTextUsername).text.toString()
        var pass= findViewById<EditText>(R.id.editTextPassword).text.toString()
        var log= dbHandler.logInApp(user,pass)

        if (log>=1){    //LOGIN SUCCESSFUL

            var preferencias: SharedPreferences = getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
            var editorPrefs: SharedPreferences.Editor= preferencias.edit()
            editorPrefs.putString("Username", user)
            editorPrefs.putString("AccessToken", pass)
            editorPrefs.putString("ID", log.toString())
            if (editorPrefs.commit()){
                Log.println(Log.ASSERT,"Preferences","OK")
            }else{
                Log.println(Log.ASSERT,"Preferences","Error")
            }
            Toast.makeText(this, "Login Succeeded", Toast.LENGTH_SHORT).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else if (log==0){  //LOGIN INCORRECT
            Toast.makeText(this, "Login Incorrect", Toast.LENGTH_SHORT).show()
        }else{ //EMPTY CREDENTIALS
            Toast.makeText(this, "Empty Credentials", Toast.LENGTH_SHORT).show()
        }
    }
}

