package com.example.valmir.kotlinMvpDagger2.adapter

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.model.Cast
import com.example.valmir.kotlinMvpDagger2.util.Constants
import de.hdodenhof.circleimageview.CircleImageView

class CastingViewHolder(noteView: View): RecyclerView.ViewHolder(noteView) {
    private val avatar = noteView.findViewById<CircleImageView>(R.id.avatar_casting)
    private val character = noteView.findViewById<TextView>(R.id.character_name_casting)
    private val name = noteView.findViewById<TextView>(R.id.actor_name_casting)

    fun fillData(cast: Cast){
        var bitmap: Bitmap
        cast.profilePath?.let {
            Glide.with(avatar.context)
                    .asBitmap()
                    .load(Constants.BASE_URL_IMAGE_W185+it)
                    .listener(object: RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            resource?.let {
                                bitmap = it
                                avatar.setImageBitmap(bitmap)
                            }
                            return true
                        }
                    })
                    .submit()
        }

        character.text = cast.character
        name.text = cast.name
    }
}