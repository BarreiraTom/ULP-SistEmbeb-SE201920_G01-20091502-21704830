package pt.ulp.se201920_g01_20091502_21704830.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import pt.ulp.se201920_g01_20091502_21704830.Adapters.adapter_notificacoes
import pt.ulp.se201920_g01_20091502_21704830.DatabaseHelper
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_notificacao
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_seguros

import pt.ulp.se201920_g01_20091502_21704830.R
import java.util.function.LongFunction

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Four Wheeler")

        val view:View = inflater.inflate(R.layout.fragment_home, container, false)

        var preferences = this.activity!!.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        Log.println(Log.ASSERT,"userID",preferences.getString("ID", "").toString())

        var qry= DatabaseHelper(this.activity!!, null).generalInfo(preferences.getString("ID", "").toString())

        //var imgByte= qry?.getString(qry.getColumnIndex("FOTO")) as ByteArray?
        var img= qry?.getInt(qry.getColumnIndex("FOTO"))!!
        view.findViewById<ImageView>(R.id.Foto_Veiculo).setImageResource(img)//setImageBitmap(BitmapFactory.decodeByteArray(imgByte, 0, imgByte!!.size))
        view.findViewById<TextView>(R.id.User_Name).text=qry?.getString(qry.getColumnIndex("NOME"))!!
        view.findViewById<TextView>(R.id.User_Email).text=qry?.getString(qry.getColumnIndex("EMAIL"))!!
        view.findViewById<TextView>(R.id.Empr_Name).text=qry?.getString(qry.getColumnIndex("USERNAME"))!!
        view.findViewById<TextView>(R.id.Marca_Veic).text=qry?.getString(qry.getColumnIndex("MARCA"))!!
        view.findViewById<TextView>(R.id.Matricula_Vaic).text=qry?.getString(qry.getColumnIndex("MATRICULA"))!!
        view.findViewById<TextView>(R.id.Ano_Veic).text=qry?.getString(qry.getColumnIndex("ANO"))!!
        view.findViewById<TextView>(R.id.Modelo_Veic).text=qry?.getString(qry.getColumnIndex("MODELO"))!!
        view.findViewById<TextView>(R.id.Seguro_Veic).text="${qry?.getString(qry.getColumnIndex("DATA_FIM"))!!} - ${qry?.getString(qry.getColumnIndex("NOME_SEG"))!!}"

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        var mensagens= emptyList<dataclass_notificacao>()
        var preferences = this.activity!!.getSharedPreferences("GrupoIPreferences", Context.MODE_PRIVATE)
        var qry= DatabaseHelper(this.activity!!, null).getNotifSeg(preferences.getString("ID", "").toString())
        if (qry!=null && qry.getCount()>0) {
            do {
                mensagens += listOf(dataclass_notificacao("O seu seguro com '" + qry?.getString(qry.getColumnIndex("NOME"))!!+ "' acaba a",
                                                               qry?.getString(qry.getColumnIndex("DATA_FIM"))!!))
            } while (qry!!.moveToNext())

            recycler_view_home_notifs.layoutManager = LinearLayoutManager(activity)
            recycler_view_home_notifs.adapter = adapter_notificacoes(mensagens)
        }
        super.onActivityCreated(savedInstanceState)
    }
}
