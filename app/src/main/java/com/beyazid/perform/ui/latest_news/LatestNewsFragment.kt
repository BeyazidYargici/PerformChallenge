package com.beyazid.perform.ui.latest_news

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseFragment
import com.beyazid.perform.network.Status
import com.beyazid.perform.model.latests_news.LatestNewsItem
import com.beyazid.perform.utils.createDialog
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import gone
import init
import kotlinx.android.synthetic.main.fragment_latest_news.*
import javax.inject.Inject


class LatestNewsFragment : BaseFragment() {

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun getLayout(): Int = R.layout.fragment_latest_news

    @Inject
    lateinit var vmFactory: LatestNewsVMFactory

    @Inject
    lateinit var viewModel: LatestNewsViewModel

    lateinit var adapter: LatestNewsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(LatestNewsViewModel::class.java)
        progressBall = fr_latest_news_pb
        getData()
    }

    /**
     *  fetch latest news from repository -> control the response status -> fill the adapter if status returned as success
     */
    private fun getData() {
        viewModel.getLatestNews().invokeOnCompletion {
            viewModel.status?.observe(this, Observer {
                when(viewModel.status?.value?.status){
                    Status.SUCCESS -> viewModel.latestNewsResponse?.observe(this, Observer { list-> initAdapter(list) })
                    else-> createDialog(activity!!, it.code.toString(), it.message!!)
                }
                progressBall.gone()
            })

        }
    }

    private fun initAdapter(newsList: List<LatestNewsItem>) {
        recyclerView = fr_latest_news_rv
        adapter = LatestNewsAdapter(activity!!, newsList)
        recyclerView.init(adapter)
    }

}
