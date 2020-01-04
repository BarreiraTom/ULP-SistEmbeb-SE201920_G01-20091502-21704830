package pt.ulp.se201920_g01_20091502_21704830.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_seguros.view.*
import kotlinx.android.synthetic.main.cardview_viagens.view.*
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_seguros
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.R

class adapter_seguros(val seguros : List<dataclass_seguros>): RecyclerView.Adapter<adapter_seguros.SegurosViewHolder>() {



    class SegurosViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SegurosViewHolder {
        return SegurosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_seguros , parent, false))
    }

    override fun getItemCount() = seguros.size

    override fun onBindViewHolder(holder: SegurosViewHolder, position: Int) {
        val seguro = seguros[position]

        holder.view.cardview_seguro_seguradora.text = seguro.seguradora
        holder.view.cardview_seguro_data_inicio.text = seguro.data_inicio
        holder.view.cardview_seguro_data_expiracao.text = seguro.data_expiracao


    }
}