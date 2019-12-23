package pt.ulp.se201920_g01_20091502_21704830

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class PrefFileHelper : AppCompatActivity(){

    val preferenceName:String= "ficheiroPreferences"
    val DbHelper= DatabaseHelper(this, null)

    fun SaveLogInCreds(user: String, pass:String, userId: String): String{
        var preferencias: SharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        var editorPrefs: SharedPreferences.Editor= preferencias.edit()
        editorPrefs.putString("Username", user)
        editorPrefs.putString("AccessToken", pass)
        editorPrefs.putString("ID", userId)
        if (editorPrefs.commit()){
            return "OK"
        }else{
            return "Error"
        }
    }

    fun LoadLogInCreds(){
        val preferences = getSharedPreferences(preferenceName,MODE_PRIVATE)
        var UserCreds = preferences.getString("Username", "")
        var PassCreds = preferences.getString("AccessToken", "")

        if (UserCreds!="" && PassCreds!="") {
            DbHelper.logInApp(UserCreds!!, PassCreds!!)
        }
    }

    fun getID():String{
        val preferences = getSharedPreferences(preferenceName,MODE_PRIVATE)
        var userId=  preferences.getString("ID", "")

        if (userId.isNullOrEmpty()){
            //TODO SEND USER TO LOGIN
        }
        return userId!!
    }

    fun EffectLogOut(): String{
        var preferencias: SharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        var editorPrefs: SharedPreferences.Editor= preferencias.edit()
        editorPrefs.remove("Username")
        editorPrefs.remove("AccessToken")
        editorPrefs.remove("ID")
        if (editorPrefs.commit()){
            return "OK"
        }else{
            return "Error"
        }
    }
}