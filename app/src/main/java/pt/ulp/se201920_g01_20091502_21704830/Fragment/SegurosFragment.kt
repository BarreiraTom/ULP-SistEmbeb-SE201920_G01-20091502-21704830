package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pt.ulp.se201920_g01_20091502_21704830.DatabaseHelper
import pt.ulp.se201920_g01_20091502_21704830.InserirSegurosActivity
import pt.ulp.se201920_g01_20091502_21704830.R

/**
 * A simple [Fragment] subclass.
 */
class SegurosFragment : Fragment() {

    //private lateinit var Dataset:Array<>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewF: View = inflater.inflate(R.layout.fragment_seguros, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Lista Seguro")

        var preferences = this.activity!!.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        var qry= DatabaseHelper(this.activity!!, null).getSegGen(preferences.getString("ID", "").toString())
        if (qry!=null && qry.getCount()>0){
            do{
                //Log.println(Log.ASSERT, "qry Array", qry?.getString(qry.getColumnIndex("DATA_FIM"))!!)

            }while(qry!!.moveToNext())
        }

        val fab: FloatingActionButton = viewF.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(context, InserirSegurosActivity::class.java)
            startActivity(intent)
        }
        return viewF
    }


}
