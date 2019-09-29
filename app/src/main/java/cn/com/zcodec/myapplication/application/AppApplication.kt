package cn.com.zcodec.myapplication.application

import android.app.Activity
import android.app.Application
import android.content.Context
import cn.com.zcodec.myapplication.di.component.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AppApplication: Application(), HasActivityInjector {

    /**
     * dagger2扩展库
     */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            //此过程专用于LeakCanary进行堆分析。在此过程中不应初始化应用程序。
            return;
        }
        LeakCanary.install(this);

        DaggerAppComponent.create().inject(this)
        appApplication = this
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    companion object Factory {

        lateinit var appApplication: AppApplication
            private set

        fun get(context: Context): AppApplication = context.applicationContext as AppApplication
    }

}