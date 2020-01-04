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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_abastecimentos.*
import kotlinx.android.synthetic.main.fragment_seguros.*
import kotlinx.android.synthetic.main.fragment_viagens.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_manutencao
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_seguros
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_viagens
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_manutencao
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_seguros
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.InserirSegurosActivity
import pt.ulp.se201920_g01_20091502_21704830.R

/**
 * A simple [Fragment] subclass.
 */
class SegurosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewF: View = inflater.inflate(R.layout.fragment_seguros, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Lista Seguro")

        val fab: FloatingActionButton = viewF.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(context, InserirSegurosActivity::class.java)
            startActivity(intent)
        }
        return viewF
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var seguros= emptyList<dataclass_seguros>()
        var preferences = this.activity!!.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        var qry= DatabaseHelper(this.activity!!, null).getSegGen(preferences.getString("ID", "").toString())
        if (qry!=null && qry.getCount()>0){
            do{
                seguros+= listOf(
                    dataclass_seguros(qry?.getString(qry.getColumnIndex("NOME"))!!,
                                           qry?.getString(qry.getColumnIndex("DATA_INI"))!!,
                                           qry?.getString(qry.getColumnIndex("DATA_FIM"))!!))
            }while(qry!!.moveToNext())

            recycler_view_seguros.layoutManager = LinearLayoutManager(activity)
            recycler_view_seguros.adapter = adapter_seguros(seguros)
        }

        super.onActivityCreated(savedInstanceState)
    }
}
