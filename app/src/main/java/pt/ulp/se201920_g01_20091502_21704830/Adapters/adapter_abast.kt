package pt.ulp.se201920_g01_20091502_21704830.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_abast.view.*
import kotlinx.android.synthetic.main.cardview_viagens.view.*
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_abast
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.R

class adapter_abast(val abastecimentos : List<dataclass_abast>): RecyclerView.Adapter<adapter_abast.AbastViewHolder>() {



    class AbastViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbastViewHolder {
        return AbastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_abast , parent, false))
    }

    override fun getItemCount() = abastecimentos.size

    override fun onBindViewHolder(holder: AbastViewHolder, position: Int) {
        val abastecimento = abastecimentos[position]

        holder.view.cardview_abast_data.text = abastecimento.data
        holder.view.cardview_abast_local.text = abastecimento.local
        holder.view.cardview_abast_quantidade.text = abastecimento.quantidade
        holder.view.cardview_abast_valor_gasto.text = abastecimento.valor_gasto


    }
}