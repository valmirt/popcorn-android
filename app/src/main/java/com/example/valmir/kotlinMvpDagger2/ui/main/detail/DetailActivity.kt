package com.example.valmir.kotlinMvpDagger2.ui.main.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.TMDBApplication
import com.example.valmir.kotlinMvpDagger2.adapter.SectionsPagerAdapter
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.ui.base.BaseActivity
import com.example.valmir.kotlinMvpDagger2.ui.main.detail.casting.CastingFragment
import com.example.valmir.kotlinMvpDagger2.ui.main.detail.info.InfoFragment
import com.example.valmir.kotlinMvpDagger2.util.Constants
import com.example.valmir.kotlinMvpDagger2.util.Constants.Companion.MOVIE_ID
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.View {
    lateinit var mSectionPagesAdapter : SectionsPagerAdapter

    @Inject
    lateinit var mPresenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        TMDBApplication.graph.inject(this)
        mPresenter.attach(this)

        val bundle = intent.extras

        mPresenter.getDetails(bundle.getInt(MOVIE_ID))
    }

    override fun successResponse(movie: Movie) {
        mSectionPagesAdapter = SectionsPagerAdapter(supportFragmentManager)
        fillPages(movie)

        viewpager_container.adapter = mSectionPagesAdapter
        tab_detail.setupWithViewPager(viewpager_container)
        viewpager_container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_detail))
        tab_detail.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager_container))
    }

    override fun errorResponse(error: String) {
        Snackbar.make(coordinator_detail, error, Snackbar.LENGTH_LONG).show()
    }

    private fun fillPages(arg: Movie) {
        fillBackDropImage(arg.posterDetail)

        val fragment1: Fragment
        val arg1 = Bundle()
        arg1.putSerializable(MOVIE_ID, arg)
        fragment1 = InfoFragment()
        fragment1.arguments = arg1
        mSectionPagesAdapter.addPages(fragment1, getString(R.string.title_tab_one))

        val fragment2: Fragment
        fragment2 = CastingFragment()
        fragment2.arguments = arg1
        mSectionPagesAdapter.addPages(fragment2, getString(R.string.title_tab_two))
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
                                val palette = Palette.from(bitmap).generate()
                                val vibrant = palette.vibrantSwatch
                                vibrant?.let {
                                    app_bar.setBackgroundColor(it.rgb)
                                    toolbar.setBackgroundColor(it.rgb)
                                    tab_detail.setBackgroundColor(it.rgb)
                                    tab_detail.setTabTextColors(it.bodyTextColor, it.titleTextColor)
                                }
                            }
                            return true
                        }
                    })
                    .submit()
        }
    }
}
