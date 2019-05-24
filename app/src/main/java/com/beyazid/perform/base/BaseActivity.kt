package com.beyazid.perform.base

import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.beyazid.perform.R
import com.beyazid.perform.utils.InternetConnectionAvailability
import com.beyazid.perform.utils.createDialog
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, LifecycleOwner, CoroutineScope {
    private var mLifecycleRegistry: LifecycleRegistry? = null

    //prevent any kind of exception or crashes
    private lateinit var job: Job
    private lateinit var progressBar: ProgressBar
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // this job will be running on the main dispatcher(main thread)

    /**
     * this method will override in activities for getting layout id
     */
    @LayoutRes
    protected abstract fun getLayout(): Int
    var isNetworkAvailable : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        job = Job()
        progressBar = ProgressBar(this)
        mLifecycleRegistry = LifecycleRegistry(this)
        networkControl()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun getLifecycle(): Lifecycle {
        return if (mLifecycleRegistry != null) {
            mLifecycleRegistry as LifecycleRegistry
        } else {
            mLifecycleRegistry = LifecycleRegistry(this)
            mLifecycleRegistry as LifecycleRegistry
        }
    }

    private fun networkControl(){
        val connectionLiveData = InternetConnectionAvailability(this)
        connectionLiveData.observe(this, Observer { isConnected ->
            isConnected?.let {
                if (!it) createDialog(this, getString(R.string.no_connection), "")
                isNetworkAvailable = it
            }
        })
    }
}