package net.azarquiel.appretrofitsteamapidrive.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import net.azarquiel.appretrofitsteamapidrive.model.Game
import kotlinx.android.synthetic.main.row_gamelist.view.*

/**
 * Created by Oscar on 15/02/2018.
 */
class AdapterGameList (val context: Context, val layout:Int, val dataList: List<Game>) : RecyclerView.Adapter<AdapterGameList.ViewHolder>(){

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

    inner class ViewHolder(viewLayout: View, val context: Context) : RecyclerView.ViewHolder(viewLayout) {

        fun bind(dataItem: Game) {

            //Log.d("#lista", dataItem.toString())
            itemView.txtTituloJuego.text = dataItem.name


            itemView.setOnClickListener({ onItemClick(dataItem) })

        }

        private fun onItemClick(dataItem: Game) {



        }

    }

}