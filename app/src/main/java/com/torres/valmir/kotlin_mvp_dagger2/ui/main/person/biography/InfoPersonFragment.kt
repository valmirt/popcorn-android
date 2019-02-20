package com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.biography

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseFragment
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.ENGLISH_LANGUAGE
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.PERSON
import com.torres.valmir.kotlin_mvp_dagger2.utils.libs.Utils
import kotlinx.android.synthetic.main.fragment_info_person.*
import javax.inject.Inject

class InfoPersonFragment: BaseFragment(), InfoPersonContract.View {
    private var person: Person? = null
    private var language = ""
    private lateinit var preferences: SharedPreferences

    @Inject
    lateinit var mPresenter: InfoPersonContract.Presenter

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
            person = it.getSerializable(PERSON) as? Person
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_person, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fillViews()
    }

    private fun fillViews() {
        person?.let { person ->
            //birthday
            if (language != ENGLISH_LANGUAGE) birthday_person.text = Utils.refactorDate(person.birthday)
            else birthday_person.text = person.birthday

            //Deathday
            if (person.deathday.isNullOrEmpty()) {
                title_death_person.visibility = View.INVISIBLE
            } else {
                if (language != ENGLISH_LANGUAGE) deathday_person.text = Utils.refactorDate(person.deathday)
                else deathday_person.text = person.deathday
            }


            place_birth_person.text = person.placeBirth
            biography_person.text = person.biography
        }
    }
}