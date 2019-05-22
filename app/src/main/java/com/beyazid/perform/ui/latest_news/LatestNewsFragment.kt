package com.beyazid.perform.ui.latest_news

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseFragment
import com.beyazid.perform.model.latests_news.LatestNewsItem
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import init
import kotlinx.android.synthetic.main.latest_news_fragment.*
import timber.log.Timber
import javax.inject.Inject


class LatestNewsFragment : BaseFragment() {

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun getLayout(): Int = R.layout.latest_news_fragment

    @Inject
    lateinit var vmFactory: LatestNewsVMFactory

    @Inject
    lateinit var viewModel: LatestNewsViewModel

    lateinit var adapter: LatestNewsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(LatestNewsViewModel::class.java)
        initUI()
    }

    private fun initUI() {
        getData()
    }

    private fun getData() {
        viewModel.getLatestNews().invokeOnCompletion {
            viewModel.latestNewsResponse?.observe(this@LatestNewsFragment, Observer {
                initAdapter(it)
            })
        }
    }

    private fun initAdapter(newsList: List<LatestNewsItem>) {
        recyclerView = fr_latest_news_rv
        adapter = LatestNewsAdapter(activity!!, newsList)
        recyclerView.init(adapter)
    }

}
