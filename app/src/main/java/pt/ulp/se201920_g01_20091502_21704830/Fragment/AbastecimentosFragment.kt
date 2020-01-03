package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_abastecimentos.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_abast
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_abast
import pt.ulp.se201920_g01_20091502_21704830.InserirAbastActivity
import pt.ulp.se201920_g01_20091502_21704830.R

/**
 * A simple [Fragment] subclass.
 */
class AbastecimentosFragment : Fragment() {

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
        val abastecimentos = listOf(dataclass_abast("03-01-2020", "Galp", "30L", "45€"),
                                    dataclass_abast("02-01-2020", "BP", "20L", "30€"))

        recycler_view_abast.layoutManager = LinearLayoutManager(activity)
        recycler_view_abast.adapter = adapter_abast(abastecimentos)

        super.onActivityCreated(savedInstanceState)
    }
}
