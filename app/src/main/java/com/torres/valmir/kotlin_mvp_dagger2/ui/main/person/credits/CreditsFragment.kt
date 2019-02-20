package com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.credits

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.adapter.CreditsAdapter
import com.torres.valmir.kotlin_mvp_dagger2.adapter.ItemListener
import com.torres.valmir.kotlin_mvp_dagger2.model.Entity
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseFragment
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import kotlinx.android.synthetic.main.fragment_casting_detail.*
import javax.inject.Inject

class CreditsFragment: BaseFragment(), CreditsContract.View {

    @Inject
    lateinit var mPresenter: CreditsContract.Presenter

    private lateinit var mListAdapter: CreditsAdapter
    private var person: Person? = null
    private var language = ""
    private lateinit var preferences: SharedPreferences

    private val itemListener = object : ItemListener<Entity> {
        override fun onClick(item: Entity) {
            mPresenter.getEntity(item.id, language, item.mediaType, this@CreditsFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attach(this)

        val args = arguments

        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)?.let { language->
                this.language = language
            }
        }

        args?.let {
            person = it.getSerializable(Constants.PERSON) as? Person
        }

        mListAdapter = CreditsAdapter(context!!, itemListener)
    }

    override fun onResume() {
        super.onResume()
        person?.let {person ->
            mPresenter.getFilmography(person.id, language)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_casting_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_casting.setHasFixedSize(true)
        list_casting.layoutManager = LinearLayoutManager(context)
        list_casting.adapter = mListAdapter
    }

    override fun successResponse(filmography: List<Entity>?) {
        filmography?.let {
            mListAdapter.replaceData(it)
        }
    }

    override fun errorResponse(error: String) = Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()
}