package name.paynd.study.easypark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import name.paynd.study.easypark.databinding.ActivityMainBinding
import name.paynd.study.easypark.location.LocationProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var location: LocationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).daggerAppComponent.inject(this)

        super.onCreate(savedInstanceState)
    }
}