package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_seguros.*
import kotlinx.android.synthetic.main.fragment_viagens.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_seguros
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_viagens
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

        val seguros = listOf(dataclass_seguros("Tranquilidade", "16-09-2017", "16-09-2018"),
                             dataclass_seguros("Fidelidade", "16-09-2018", "16-09-2019"),
                             dataclass_seguros("Liberty", "16-09-2019", "16-09-2020"))

        recycler_view_seguros.layoutManager = LinearLayoutManager(activity)
        recycler_view_seguros.adapter = adapter_seguros(seguros)

        super.onActivityCreated(savedInstanceState)
    }
}
