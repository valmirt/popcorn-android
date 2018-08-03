package com.torres.valmir.kotlinMvpDagger2.ui.main.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.adapter.SectionsPagerAdapter
import com.torres.valmir.kotlinMvpDagger2.model.Movie
import com.torres.valmir.kotlinMvpDagger2.ui.base.BaseActivity
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.casting.CastingFragment
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.info.InfoFragment
import com.torres.valmir.kotlinMvpDagger2.util.Constants
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.MOVIE_OBJECT
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(){
    private lateinit var mSectionPagesAdapter : SectionsPagerAdapter
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        bundle?.let {
            movie = it.getSerializable(MOVIE_OBJECT) as? Movie
        }

        mSectionPagesAdapter = SectionsPagerAdapter(supportFragmentManager)
        fillPages(movie)

        viewpager_container.adapter = mSectionPagesAdapter
        tab_detail.setupWithViewPager(viewpager_container)
        viewpager_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_detail))
        tab_detail.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager_container))
    }

    private fun fillPages(arg: Movie?) {

        arg?.let { temp ->
            fillBackDropImage(temp.posterDetail)

            val fragment1: Fragment
            val arg1 = Bundle()
            arg1.putSerializable(MOVIE_OBJECT, temp)
            fragment1 = InfoFragment()
            fragment1.arguments = arg1
            mSectionPagesAdapter.addPages(fragment1, getString(R.string.title_tab_one))

            val fragment2: Fragment
            fragment2 = CastingFragment()
            fragment2.arguments = arg1
            mSectionPagesAdapter.addPages(fragment2, getString(R.string.title_tab_two))
        }
    }

    private fun fillBackDropImage(backdropImage: String?) {
        var bitmap: Bitmap
        backdropImage?.let {
            Glide.with(backdrop_image_detail.context)
                    .asBitmap()
                    .load(Constants.BASE_URL_IMAGE_W500 +it)
                    .listener(object: RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap?, model: Any?, target: com.bumptech.glide.request.target.Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            resource?.let {
                                bitmap = it
                                backdrop_image_detail.setImageBitmap(bitmap)
                            }
                            return true
                        }
                    })
                    .submit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
