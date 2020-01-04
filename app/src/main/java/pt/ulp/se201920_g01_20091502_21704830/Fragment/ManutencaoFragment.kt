package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_inserir_manut.*
import pt.ulp.se201920_g01_20091502_21704830.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_abastecimentos.*
import kotlinx.android.synthetic.main.fragment_manutencao.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_abast
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_manutencao
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_abast
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_manutencao
import pt.ulp.se201920_g01_20091502_21704830.InserirManutActivity
import pt.ulp.se201920_g01_20091502_21704830.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ManutencaoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ManutencaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ManutencaoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewF: View = inflater.inflate(R.layout.fragment_manutencao, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Lista Manutenções")

        val fab: FloatingActionButton = viewF.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
                val intent = Intent(context, InserirManutActivity::class.java)
                startActivity(intent)
        }
        // Inflate the layout for this fragment
        return viewF
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var manutencoes= emptyList<dataclass_manutencao>()
        var preferences = this.activity!!.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        var qry= DatabaseHelper(this.activity!!, null).getManutGen(preferences.getString("ID", "").toString())
        if (qry!=null && qry.getCount()>0){
            do{
                manutencoes+= listOf(dataclass_manutencao(qry?.getString(qry.getColumnIndex("NOME_MEC"))!!,
                                                               qry?.getString(qry.getColumnIndex("TIPO_REPAR"))!!,
                                                               qry?.getString(qry.getColumnIndex("DATA_D"))!!,
                                                               qry?.getString(qry.getColumnIndex("DESCRICAO"))!!))
            }while(qry!!.moveToNext())

            recycler_view_manutencao.layoutManager = LinearLayoutManager(activity)
            recycler_view_manutencao.adapter = adapter_manutencao(manutencoes)
        }

        super.onActivityCreated(savedInstanceState)
    }
}
