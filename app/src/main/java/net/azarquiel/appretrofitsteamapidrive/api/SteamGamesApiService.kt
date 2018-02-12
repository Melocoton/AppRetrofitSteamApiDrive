package net.azarquiel.appretrofitsteamapidrive.api

import net.azarquiel.appretrofitsteamapidrive.model.Juego
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

/**
 * Created by Oscar on 11/02/2018.
 */
interface SteamGamesApiService {

    //peticion get, nos devuelve una lista de juegos en un Call
    @GET("steamgames/api.php?op=getGames")
    fun listaJuegos() : Call<List<Juego>>

    //peticion post, mete los datos que le indiquemos con @Field para indicar cada paramtetro o
    //con @Body para serializar un objeto (en este caso usamos field porque los parametros
    //son distintos de las propiedades del objeto Juego
    //antes de poner un parametro hay que quitar las comillas simples
    @POST("/steamgames/api.php?op=putGame")
    @FormUrlEncoded //esto es necesario
    fun ponerJuego(
            @Field("appid") AppID: String,
            @Field("name") Nombre: String,
            @Field("desc") Descripcion: String,
            @Field("img") Imagen: String,
            @Field("web") Link: String
    ) : Call<String>

    //fun getData(): Observable<Juego>

//    companion object {
//        fun create(): SteamGamesApiService {
//            val retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .baseUrl("http://estomelohancambiado.com/")
//                    .build()
//            return retrofit.create(SteamGamesApiService::class.java)
//        }
//    }

}