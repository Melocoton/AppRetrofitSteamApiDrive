package net.azarquiel.appretrofitsteamapidrive.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.azarquiel.appretrofitsteamapidrive.R
import net.azarquiel.appretrofitsteamapidrive.adapter.CustomAdapter
import net.azarquiel.appretrofitsteamapidrive.api.SteamGamesApiService
import net.azarquiel.appretrofitsteamapidrive.model.Juego
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //testApi()
        getGamesApi()

    }

    private fun getGamesApi(){
        //preparamos retrofit
        val retrofit = Retrofit.Builder()
                .baseUrl("http://estomelohancambiado.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val cliente: SteamGamesApiService = retrofit.create(SteamGamesApiService::class.java)

        //preparamos la llamada de la peticion get
        val llamada: Call<List<Juego>> = cliente.listaJuegos()

        doAsync {
            val lista = llamada.execute().body()
            uiThread {
                cargarDatos(lista)
            }
        }
    }

    private fun cargarDatos(lista: List<Juego>){
        var adapter = CustomAdapter(this,R.layout.row_main, lista)
        rvJuegos.layoutManager = LinearLayoutManager(this)
        rvJuegos.adapter = adapter
    }

    private fun testApi() {

        //preparamos retrofit
        val retrofit = Retrofit.Builder()
                .baseUrl("http://estomelohancambiado.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val cliente: SteamGamesApiService = retrofit.create(SteamGamesApiService::class.java)

        //preparamos la llamada de la peticion get
        val llamada: Call<List<Juego>> = cliente.listaJuegos()

        //preparamos la llamada de la peticion post
        val appid = "-2"
        val nombre = "test2"
        val descripcion = "test2"
        val imagen = "test2"
        val website = "test2"
        val llamada2: Call<String> = cliente.ponerJuego(appid,nombre,descripcion,imagen,website)

        //lanzamos las peticiones en un hilo y mostramos el resultado
        doAsync {
            val respuesta = llamada2.execute().body()
            val listaJuego = llamada.execute().body()
            //Log.d("###", llamada2.request().url().toString())
            uiThread {
                Log.d("###", respuesta)
                for (juego in listaJuego){
                    Log.d("###", juego.Nombre)
                }
            }
        }

//        //con hilos anko
//        doAsync {
//            val listaJuego = llamada.execute().body()
//            uiThread {
//                for (juego in listaJuego){
//                    Log.d("###", juego.Nombre)
//                }
//            }
//        }

//        //con llamada asincrona retrofit
//        llamada.enqueue(object : Callback<List<Juego>> {
//
//            override fun onResponse(call: Call<List<Juego>>, response: Response<List<Juego>>) {
//                val repos = response.body()
//                for (r in repos){
//                    Log.d("###", r.Nombre)
//                }
//            }
//
//            override fun onFailure(call: Call<List<Juego>>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "error :(", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
