package net.azarquiel.appretrofitsteamapidrive.model

/**
 * Created by Oscar on 06/02/2018.
 */
import com.google.gson.annotations.SerializedName

// Class para la SteamApiService Get todos los games
data class Games(val applist:Apps)
data class Apps(val apps:List<Game>)
data class Game(val appid:String, val name:String)