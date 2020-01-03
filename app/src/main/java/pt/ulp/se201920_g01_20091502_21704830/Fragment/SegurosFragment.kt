package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
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


}
