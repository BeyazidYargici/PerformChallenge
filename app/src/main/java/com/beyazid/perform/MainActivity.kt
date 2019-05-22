package com.beyazid.perform

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.beyazid.perform.base.BaseActivity
import com.google.gson.Gson
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.widget.AdapterView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.beyazid.perform.component.CustomSpinnerAdapter
import kotlinx.android.synthetic.main.custom_toolbar_with_spinner.*
import openFragment


class MainActivity : BaseActivity() {

    lateinit var navController: NavController

    override fun getLayout(): Int = R.layout.activity_main

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onSupportNavigateUp() = findNavController(R.id.ac_main_fragment_container).navigateUp()

    private var headerList = arrayOf("News", "Scores", "Standings")
    private var spinnerToolbar: Spinner? = null

    @Inject
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.ac_main_fragment_container)
        setSpinner()
        spinnerListener()
    }


    private fun spinnerListener() {
        spinnerToolbar!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position){
                    0 -> openFragment(this@MainActivity,R.id.latestNewsFragment,null)
                    1 -> openFragment(this@MainActivity,R.id.scoresFragment,null)
                    2 -> openFragment(this@MainActivity,R.id.standingsFragment,null)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun setSpinner() {
        spinnerToolbar = ac_main_expandable_list_view as Spinner
        val customAdapter = CustomSpinnerAdapter(applicationContext, headerList)
        spinnerToolbar!!.adapter = customAdapter
    }

}

//fun getData() = launch {
//        val xml = apiService.getLatestNewsAsStringAsync().await()
//        val xml1 = apiService.getScoresAsStringAsync().await()
//        val latestsNewsResponse = xmlToJson(gson, xml, LatestNewsResponse::class.java)
//        val scoresResponse = xmlToJson(gson, xml1, ScoresResponse::class.java)
//        Timber.e(latestsNewsResponse.toString())
//}