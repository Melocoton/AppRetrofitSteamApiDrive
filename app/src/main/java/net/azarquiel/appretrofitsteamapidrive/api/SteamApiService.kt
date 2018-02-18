package net.azarquiel.appretrofitsteamapidrive.api

import net.azarquiel.appretrofitsteamapidrive.model.Games
import net.azarquiel.appretrofitsteamapidrive.model.Respuesta
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by Oscar on 06/02/2018.
 */
interface SteamApiService {

    @GET("ISteamApps/GetAppList/v2")
    fun listaApps(): Call<Games>

    @GET("IPlayerService/GetOwnedGames/v0001/?key=91EB0D3B50B84C166A5C1CCEC6FBCEE8&steamid=76561197979408421&include_appinfo=1&include_played_free_games=1")
    fun listaGames(): Call<Respuesta>

//    companion object {
//        fun create(): SteamApiService {
//            val retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .baseUrl("http://api.steampowered.com/")
//                    .build()
//            return retrofit.create(SteamApiService::class.java)
//        }
//    }

}