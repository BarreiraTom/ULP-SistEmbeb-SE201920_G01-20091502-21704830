package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_abastecimentos.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_abast
import pt.ulp.se201920_g01_20091502_21704830.DatabaseHelper
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_abast
import pt.ulp.se201920_g01_20091502_21704830.InserirAbastActivity
import pt.ulp.se201920_g01_20091502_21704830.R

/**
 * A simple [Fragment] subclass.
 */
class AbastecimentosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewF: View = inflater.inflate(R.layout.fragment_abastecimentos, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Lista Abastecimento")

        val fab: FloatingActionButton = viewF.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(context, InserirAbastActivity::class.java)
            startActivity(intent)
        }
        return viewF
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var abastecimentos= emptyList<dataclass_abast>()
        var preferences = this.activity!!.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        var qry= DatabaseHelper(this.activity!!, null).getAbastGen(preferences.getString("ID", "").toString())
        if (qry!=null && qry.getCount()>0){
            do{
                abastecimentos+= listOf(dataclass_abast(qry?.getString(qry.getColumnIndex("DATA_A"))!!,
                                                        qry?.getString(qry.getColumnIndex("LOCAL"))!!,
                                                        qry?.getString(qry.getColumnIndex("QUANT"))!!+" L",
                                                        qry?.getString(qry.getColumnIndex("VALOR_GAST"))!!+" €"))
            }while(qry!!.moveToNext())

            recycler_view_abast.layoutManager = LinearLayoutManager(activity)
            recycler_view_abast.adapter = adapter_abast(abastecimentos)
        }

        super.onActivityCreated(savedInstanceState)
    }
}
