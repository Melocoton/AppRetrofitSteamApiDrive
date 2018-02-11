package net.azarquiel.appretrofitsteamapidrive.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Oscar on 11/02/2018.
 */
// Class para la SteamApiService Get todos los games
data class Games(val applist:Apps)
data class Apps(val apps:List<Game>)
data class Game(val appid:String,
                val name:String)

// Class para la SteamStoreService Get un solo game
data class GameStore(val data:Data)
data class Data(val name:String,
                val short_description:String,
                val header_image:String,
                val website:String)

// Class para la SteamGamesApi
data class Juego(val appID:String,
                 val Nombre:String,
                 val Descripcion:String,
                 val Imagen:String,
                 val Link:String)


//// Class para la SteamServiceDriveGet Get todos nuestros pokemos de Drive
//data class DriveResponse (var table:Table)
//data class Table (var rows : List<Rows>)
//data class Rows (@SerializedName("c") var column : List<Column>)
//data class Column (@SerializedName("v") var value : String, @SerializedName("f") var format : String)
//// class para volcar Games de Drive que están en Rows y Columns
//// en Objetos mas bonitos. O uno de SteamStore que está en Data
//data class GameDrive(var id:String="", var name:String = "",var descripcion:String="", var image:String="", var link:String="")