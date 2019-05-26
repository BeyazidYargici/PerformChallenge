package com.beyazid.perform.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.beyazid.perform.MainActivity
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_splash.*
import launcherAnim
import javax.inject.Inject

class SplashActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_splash

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var mDelayHandler: Handler? = null
    private val splashDelay: Long = 3000 //3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        //Initialize the Handler
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, splashDelay)

        // Rotation animation and bouncing animation
        ac_splash_iv.animate().rotation(300f).duration = (splashDelay-0.8).toLong()
        ac_splash_iv.launcherAnim()
    }

    /**
     *  MainActivity starts 3 seconds later when Application started
     */
    private val mRunnable = Runnable {
        if (!isFinishing) {
            if (isNetworkAvailable){
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    public override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}
