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
import net.azarquiel.appretrofitsteamapidrive.R
import net.azarquiel.appretrofitsteamapidrive.api.SteamStoreService
import net.azarquiel.appretrofitsteamapidrive.model.GameStore
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Retrofit
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.squareup.picasso.Picasso
import net.azarquiel.appretrofitsteamapidrive.api.SteamGamesApiService
import net.azarquiel.appretrofitsteamapidrive.model.Data
import retrofit2.converter.gson.GsonConverterFactory


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
            val url = "http://media.steampowered.com/steamcommunity/public/images/apps/${dataItem.appid}/${dataItem.img_icon_url}.jpg"
            Picasso.with(context).load(url).into(itemView.imgIcon)

            itemView.setOnClickListener({ onItemClick(dataItem) })

        }

        private fun onItemClick(dataItem: Game) {

            Log.d("###", dataItem.toString())
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://store.steampowered.com/api/")
                    .build()
            val cliente: SteamStoreService = retrofit.create(SteamStoreService::class.java)
            val llamada: Call<ResponseBody> = cliente.getData(dataItem.appid)

            doAsync {
                val resultado = llamada.execute().body().string()
                uiThread {
                    Log.d("###", resultado)
                    val json = limpiaString(resultado)
                    val juego:GameStore = Gson().fromJson(json, GameStore::class.java)
                    Log.d("###", juego.data.toString())
                    guardarJuego(juego.data)

//                    if (resultado.contains("\"success\":true")){
//                        Log.d("###", "true")
//                        val json = limpiaString(resultado)
//                        Log.d("###", json)
//                        //val juego: GameStore = Gson().toJson(json, GameStore::class.java)
//                        val juego:GameStore = Gson().fromJson(json, GameStore::class.java)
//                        Log.d("###", juego.toString())
//                    }
                }
            }

        }

        private fun guardarJuego(juego:Data){
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://estomelohancambiado.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val cliente: SteamGamesApiService = retrofit.create(SteamGamesApiService::class.java)
            val llamada: Call<String> = cliente.ponerJuego(juego.steam_appid,juego.name,
                    juego.about_the_game.replace("'",""),juego.header_image,juego.website)
            doAsync {
                val respuesta = llamada.execute().body()
                Log.d("###", respuesta)
                uiThread {
                    if (respuesta.equals("true")){
                        Toast.makeText(context, "Juego añadido", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        private fun limpiaString(json:String): String {
            var newjson=json
            newjson = newjson.substring(newjson.indexOf(":"))
            newjson = newjson.substring(1)
            newjson = newjson.substring(0,newjson.length-1)

            return newjson
        }

//        private fun comprobarJuego(resultado: String): Boolean{
//            if (resultado.contains("\"success\":true"))
//                return true
//
//            return false
//        }

    }

}