package pt.ulp.se201920_g01_20091502_21704830.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_manutencoes.view.*
import kotlinx.android.synthetic.main.cardview_viagens.view.*
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_manutencao
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.R

class adapter_manutencao(val manutencoes : List<dataclass_manutencao>): RecyclerView.Adapter<adapter_manutencao.ManutencaoViewHolder>() {



    class ManutencaoViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManutencaoViewHolder {
        return ManutencaoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_manutencoes , parent, false))
    }

    override fun getItemCount() = manutencoes.size

    override fun onBindViewHolder(holder: ManutencaoViewHolder, position: Int) {
        val manutencao = manutencoes[position]

        holder.view.cardview_manut_mecanico.text = manutencao.mecanico
        holder.view.cardview_manut_reparacao.text = manutencao.reparacao
        holder.view.cardview_manut_data.text = manutencao.data
        holder.view.cardview_manut_observacoes.text = manutencao.observacoes


    }
}