package pt.ulp.se201920_g01_20091502_21704830.Fragment


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*

import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import pt.ulp.se201920_g01_20091502_21704830.Fragment.*
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
class ManutencaoFragment : Fragment(), InserirManutencaoFragment.OnFragmentInteractionListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var inserirManutencaoFragment: InserirManutencaoFragment

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

        inserirManutencaoFragment = InserirManutencaoFragment.newInstance("", "")

        val fab: FloatingActionButton = viewF.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            activity!!.supportFragmentManager.beginTransaction().remove(ManutencaoFragment())
                .replace(R.id.nav_host_fragment, inserirManutencaoFragment)
                .addToBackStack(inserirManutencaoFragment.toString())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }

        // Inflate the layout for this fragment
        return viewF
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
