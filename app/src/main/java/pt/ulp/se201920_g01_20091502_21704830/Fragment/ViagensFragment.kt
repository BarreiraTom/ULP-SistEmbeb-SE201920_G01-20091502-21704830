package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_viagens.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_viagens
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.InserirViagensActivity
import pt.ulp.se201920_g01_20091502_21704830.R

/**
 * A simple [Fragment] subclass.
 */
class ViagensFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewF: View = inflater.inflate(R.layout.fragment_viagens, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Lista Viagens")

        val fab: FloatingActionButton = viewF.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(context, InserirViagensActivity::class.java)
            startActivity(intent)
        }

        return viewF
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val viagens = listOf(dataclass_viagens("Santa Maria da Feira", "Porto", "02-01-2020"),
                             dataclass_viagens("Santa Maria da Feira", "OAz", "03-01-2020"))

        recycler_view_viagens.layoutManager = LinearLayoutManager(activity)
        recycler_view_viagens.adapter = adapter_viagens(viagens)
        super.onActivityCreated(savedInstanceState)
    }
}
