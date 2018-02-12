package net.azarquiel.appretrofitsteamapidrive.api

import net.azarquiel.appretrofitsteamapidrive.model.Games
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

    @GET("ISteamApps/GetAppList/v0002/")
    fun listaJuegos(): Call<List<Games>>

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