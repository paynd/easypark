package name.paynd.study.easypark.ui.map

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import name.paynd.study.easypark.R

class MapFragment : Fragment(R.layout.fragment_map) {
    private val args: MapFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("####", "args.cityName: ${args.cityName}")
    }

}