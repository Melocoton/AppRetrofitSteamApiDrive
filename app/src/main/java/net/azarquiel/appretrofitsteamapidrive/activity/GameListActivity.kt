package net.azarquiel.appretrofitsteamapidrive.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_gamelist.*
import net.azarquiel.appretrofitsteamapidrive.R
import net.azarquiel.appretrofitsteamapidrive.adapter.AdapterGameList
import net.azarquiel.appretrofitsteamapidrive.api.SteamApiService
import net.azarquiel.appretrofitsteamapidrive.model.Games
import net.azarquiel.appretrofitsteamapidrive.model.Game
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Oscar on 13/02/2018.
 */
class GameListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelist)

        //testApi()
        cargarLista()

    }

    private fun cargarLista() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.steampowered.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val cliente: SteamApiService = retrofit.create(SteamApiService::class.java)

        val llamada: Call<Games> = cliente.listaApps()

        doAsync {
            val resultado = llamada.execute().body()
            uiThread {
                //Log.d("###", resultado.toString())
                //resultado[0].applist.apps[0].name
                val listaJuegos = resultado.applist.apps
                cargarApps(listaJuegos)
            }
        }
    }

    private fun cargarApps(lista: List<Game>) {
        var adapter = AdapterGameList(this,R.layout.row_gamelist, lista)
        rvLista.layoutManager = LinearLayoutManager(this)
        rvLista.adapter = adapter
    }

    private fun testApi(){
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.steampowered.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val cliente: SteamApiService = retrofit.create(SteamApiService::class.java)

        val llamada: Call<Games> = cliente.listaApps()

        doAsync {
            val resultado = llamada.execute().body()
            uiThread {
                Log.d("###", resultado.toString())
                //resultado[0].applist.apps[0].name
                val listaJuegos = resultado.applist.apps
            }
        }
    }

}