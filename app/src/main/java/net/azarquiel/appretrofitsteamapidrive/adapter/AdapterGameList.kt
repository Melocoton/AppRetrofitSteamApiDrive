package net.azarquiel.appretrofitsteamapidrive.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.google.gson.Gson
import net.azarquiel.appretrofitsteamapidrive.model.Game
import kotlinx.android.synthetic.main.row_gamelist.view.*
import net.azarquiel.appretrofitsteamapidrive.api.SteamStoreService
import net.azarquiel.appretrofitsteamapidrive.model.GameStore
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Retrofit

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

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://store.steampowered.com/api/")
                    .build()
            val cliente: SteamStoreService = retrofit.create(SteamStoreService::class.java)
            val llamada: Call<ResponseBody> = cliente.getData(dataItem.appid)

            doAsync {
                val resultado = llamada.execute().body().string()
                uiThread {
                    Log.d("###", resultado)
                    if (resultado.contains("\"success\":true")){
                        Log.d("###", "true")
                        val json = limpiaString(resultado)
                        Log.d("###", json)
                        val juego: GameStore = Gson().toJson(json, GameStore::class.java)
                    }
                }
            }

        }

        private fun limpiaString(json:String):String {
            var newjson=json
            newjson = newjson.substring(newjson.indexOf(":"))
            newjson = newjson.substring(1)
            newjson = newjson.substring(0,newjson.length-1)

            return newjson
        }

    }

}