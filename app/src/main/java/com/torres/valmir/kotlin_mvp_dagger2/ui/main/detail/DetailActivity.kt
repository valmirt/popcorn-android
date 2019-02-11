package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.adapter.SectionsPagerAdapter
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.Trailer
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season.SeasonFragment
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TVSHOW_OBJECT
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.View {

    @Inject
    lateinit var mPresenter: DetailContract.Presenter

    private lateinit var mSectionPagesAdapter : SectionsPagerAdapter
    private var movie: Movie? = null
    private var tvShow: TvShow? = null
    private lateinit var preferences: SharedPreferences
    private var language = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        mPresenter.attach(this)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferences = getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
        preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)?.let {
            language = it
        }

        val bundle = intent.extras
        bundle?.let {
            movie = it.getSerializable(MOVIE_OBJECT) as? Movie
            tvShow = it.getSerializable(TVSHOW_OBJECT) as? TvShow
        }

        mSectionPagesAdapter = SectionsPagerAdapter(supportFragmentManager)
        fillPages(movie, tvShow)

        viewpager_container.adapter = mSectionPagesAdapter
        tab_detail.setupWithViewPager(viewpager_container)
        viewpager_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_detail))
        tab_detail.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager_container))
    }

    private fun fillPages(movie: Movie?, tvShow: TvShow?) {

        movie?.let { temp ->
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

        tvShow?.let {temp ->
            fillBackDropImage(temp.posterDetail)

            val fragment1: Fragment
            val arg1 = Bundle()
            arg1.putSerializable(TVSHOW_OBJECT, temp)
            fragment1 = InfoFragment()
            fragment1.arguments = arg1
            mSectionPagesAdapter.addPages(fragment1, getString(R.string.title_tab_one))

            val fragment2: Fragment
            fragment2 = CastingFragment()
            fragment2.arguments = arg1
            mSectionPagesAdapter.addPages(fragment2, getString(R.string.title_tab_two))

            val fragment3: Fragment
            fragment3 = SeasonFragment()
            fragment3.arguments = arg1
            mSectionPagesAdapter.addPages(fragment3, getString(R.string.title_tab_three))
        }
    }

    private fun fillBackDropImage(backdropImage: String?) {
        var bitmap: Bitmap
        backdropImage?.let {poster->
            Glide.with(backdrop_image_detail.context)
                    .asBitmap()
                    .load(Constants.BASE_URL_IMAGE_W780 + poster)
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

    override fun setupTrailers(trailers: List<Trailer>?) {
        if (trailers!= null && !trailers.isEmpty()) {
            if (trailers.size > 1) {
                val nameTrailers = ArrayList<String>()
                for (trailer in trailers) {
                    if (trailer.site == "YouTube") {
                        nameTrailers.add(trailer.name)
                    }
                }
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.trailers)
                builder.setItems(nameTrailers.toTypedArray()) { dialog, position ->
                    for (trailer in trailers) {
                        if (trailer.name == nameTrailers[position]) {
                            mPresenter.sendToYoutube(trailer.key)
                            dialog.dismiss()
                        }
                    }
                }
                builder.setNegativeButton(R.string.colse) { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create()
                builder.show()
            } else {
                if (trailers[0].site == "YouTube") {
                    mPresenter.sendToYoutube(trailers[0].key)
                } else errorResponse(getString(R.string.error_trailer))
            }
        } else errorResponse(getString(R.string.error_trailer))
    }

    override fun errorResponse(error: String) {
        Snackbar.make(coordinator_detail, error, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                if (isTaskRoot) {
                    mPresenter.sendToHomeActivity(this)
                } else onBackPressed()
            }
            R.id.trailer_button -> {
                movie?.id?.let {
                    mPresenter.getTrailersMovie(it, language)
                }
                tvShow?.id?.let {
                    mPresenter.getTrailersTV(it, language)
                }
            }
            R.id.share_button -> {
                movie?.id?.let {
                    mPresenter.shareMovieOrTvShow(it, "movie")
                }
                tvShow?.id?.let {
                    mPresenter.shareMovieOrTvShow(it, "tv")
                }
            }
        }
        return true
    }
}
