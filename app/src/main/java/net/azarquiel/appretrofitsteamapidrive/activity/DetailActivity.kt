package net.azarquiel.appretrofitsteamapidrive.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.azarquiel.appretrofitsteamapidrive.R
import net.azarquiel.appretrofitsteamapidrive.model.Juego

/**
 * Created by Oscar on 13/02/2018.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val juego: Juego = intent.getSerializableExtra("juegoPulsado") as Juego
        txtNombre.text = juego.Nombre
        //txtDescripcion.text = juego.Descripcion
        txtDescripcion.text = Html.fromHtml(juego.Descripcion)
        //Picasso.with(context).load(dataItem.Imagen).into(itemView.imgLogo)
        Picasso.with(this).load(juego.Imagen).into(imagen)
    }

}