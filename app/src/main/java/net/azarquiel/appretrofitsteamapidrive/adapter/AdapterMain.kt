package net.azarquiel.appretrofitsteamapidrive.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import net.azarquiel.appretrofitsteamapidrive.model.Juego
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_main.view.*
import net.azarquiel.appretrofitsteamapidrive.activity.DetailActivity

/**
 * Created by Oscar on 12/02/2018.
 */
class AdapterMain(val context: Context, val layout:Int, val dataList: List<Juego>) : RecyclerView.Adapter<AdapterMain.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewLayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewLayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class  ViewHolder(viewLayout: View, val context: Context) : RecyclerView.ViewHolder(viewLayout) {
        fun bind(dataItem: Juego) {

            itemView.imgLogo
            Picasso.with(context).load(dataItem.Imagen).into(itemView.imgLogo)

            itemView.setOnClickListener({ onItemClick(dataItem)})

        }

        private fun onItemClick(dataItem: Juego){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("juegoPulsado", dataItem)
            context.startActivity(intent)
        }

    }

}