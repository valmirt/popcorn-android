package com.torres.valmir.kotlin_mvp_dagger2.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.Entity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants

class CreditsAdapter (val context: Context,
                      private val itemListener: ItemListener<Entity>) : RecyclerView.Adapter<CreditsAdapter.CreditViewHolder>() {

    private val data = ArrayList<Entity>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val noteView = inflater.inflate(R.layout.item_credits, parent, false)
        return CreditViewHolder(noteView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val entity = data[position]

        holder.fillData(entity, context)

        holder.itemView.setOnClickListener {
            itemListener.onClick(entity)
        }
    }

    fun replaceData(data: List<Entity>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class CreditViewHolder(noteView: View): RecyclerView.ViewHolder(noteView) {
        private val image = noteView.findViewById<ImageView>(R.id.credit_poster)
        private val title = noteView.findViewById<TextView>(R.id.credit_title)
        private val character = noteView.findViewById<TextView>(R.id.credit_original_title)

        fun fillData(data: Entity, context: Context) {
            image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_image_default_poster))
            title.text = ""
            character.text = ""

            if (!data.title.isEmpty() && !data.originalTitle.isEmpty()) {
                title.text = data.title
            }

            if (!data.name.isEmpty() && !data.originalName.isEmpty()) {
                title.text = data.name
            }

            character.text = data.character

            var bitmap: Bitmap
            data.posterUrl?.let { poster->
                Glide.with(image.context)
                        .asBitmap()
                        .load(Constants.BASE_URL_IMAGE_W185+poster)
                        .listener(object: RequestListener<Bitmap> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let {
                                    bitmap = it
                                    image.setImageBitmap(bitmap)
                                }
                                return true
                            }
                        })
                        .submit()
            }
        }
    }
}