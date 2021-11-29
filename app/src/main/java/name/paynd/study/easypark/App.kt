package name.paynd.study.easypark

import android.app.Application
import name.paynd.study.easypark.di.AppComponent
import name.paynd.study.easypark.di.DaggerAppComponent

class App : Application() {

    val daggerAppComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}