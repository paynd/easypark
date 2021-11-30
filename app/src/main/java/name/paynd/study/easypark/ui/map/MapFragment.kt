package name.paynd.study.easypark.ui.map

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import name.paynd.study.easypark.App
import name.paynd.study.easypark.R
import name.paynd.study.easypark.api.toPoint
import name.paynd.study.easypark.databinding.FragmentMapBinding
import name.paynd.study.easypark.di.VMFactory
import name.paynd.study.easypark.location.LocationState
import name.paynd.study.easypark.ui.map.MapUtil.createPointAnnotation
import name.paynd.study.easypark.ui.map.MapUtil.createPolygonAnnotation
import name.paynd.study.easypark.ui.map.MapUtil.flyTo
import javax.inject.Inject

class MapFragment : Fragment(R.layout.fragment_map) {
    private val args: MapFragmentArgs by navArgs()

    @Inject
    lateinit var vmFactory: VMFactory
    private val viewModel: MapVM by viewModels { vmFactory }
    private val viewBinding by viewBinding(FragmentMapBinding::bind)

    override fun onAttach(context: Context) {
        (activity?.application as App).daggerAppComponent.inject(this)
        super.onAttach(context)

        lifecycleScope.launchWhenResumed {
            viewModel.updateHomeCity(args.cityName).collect { city ->
                city?.let {
                    with(viewBinding.mapView) {
                        createPolygonAnnotation(city)
                        flyTo(city)
                    }
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.location.collect { state ->
                if (state is LocationState.Success) {
                    viewBinding.mapView.createPointAnnotation(state.location.toPoint())
                }
            }
        }
    }
}