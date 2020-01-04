package pt.ulp.se201920_g01_20091502_21704830.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_notificacoes.view.*
import kotlinx.android.synthetic.main.cardview_seguros.view.*
import kotlinx.android.synthetic.main.cardview_viagens.view.*
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_notificacao
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_seguros
import pt.ulp.se201920_g01_20091502_21704830.Dataclasses.dataclass_viagens
import pt.ulp.se201920_g01_20091502_21704830.R

class adapter_notificacoes(val notificacoes : List<dataclass_notificacao>): RecyclerView.Adapter<adapter_notificacoes.NotificacoesViewHolder>() {



    class NotificacoesViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificacoesViewHolder {
        return NotificacoesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_notificacoes , parent, false))
    }

    override fun getItemCount() = notificacoes.size

    override fun onBindViewHolder(holder: NotificacoesViewHolder, position: Int) {
        val notificacao = notificacoes[position]

        holder.view.cardview_notif_msg.text = notificacao.mensagem + ": " + notificacao.data_fim

    }
}