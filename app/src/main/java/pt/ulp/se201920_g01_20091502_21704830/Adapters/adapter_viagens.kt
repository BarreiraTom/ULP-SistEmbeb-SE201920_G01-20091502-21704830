package pt.ulp.se201920_g01_20091502_21704830.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_viagens.view.*
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.R

class adapter_viagens(val viagens : List<dataclass_viagens>): RecyclerView.Adapter<adapter_viagens.ViagensViewHolder>() {



    class ViagensViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViagensViewHolder {
        return ViagensViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_viagens , parent, false))
    }

    override fun getItemCount() = viagens.size

    override fun onBindViewHolder(holder: ViagensViewHolder, position: Int) {
        val viagem = viagens[position]

        holder.view.cardview_viagem_origem.text = viagem.origem
        holder.view.cardview_viagem_destino.text = viagem.destino
        holder.view.cardview_viagem_data.text = viagem.data_viagem


    }
}