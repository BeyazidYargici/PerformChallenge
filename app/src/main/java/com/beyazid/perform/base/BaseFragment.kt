package com.beyazid.perform.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 *  Created by beyazid on 2019-05-23.
 *  All fragments should be extended from this fragment
 */
abstract class BaseFragment : Fragment(), CoroutineScope, HasSupportFragmentInjector {

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    //prevent any kind of exception or crashes
    private lateinit var job: Job

    // progressbar will be showing while fetching data from network
    lateinit var progressBall : ProgressBar
    // RecyclerView will be listing response items
    lateinit var recyclerView: RecyclerView




    /**
     * This method will override in fragments to inflate the view
     *
     * @return the layout id which will inflated
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // this job will be running on the main dispatcher(main thread)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    /**
     * ProgressBar will be visible when fetching the data and will be gone when fetching finish
     */
//    fun progressBarVisibility(isVisible: Boolean) = if (isVisible) progressBar?.visible() else progressBar?.gone()


}