package name.paynd.study.easypark.ui.cities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import name.paynd.study.easypark.App
import name.paynd.study.easypark.R
import name.paynd.study.easypark.api.RepoResult
import name.paynd.study.easypark.databinding.FragmentCitiesBinding
import name.paynd.study.easypark.di.VMFactory
import javax.inject.Inject

class CitiesFragment : Fragment(R.layout.fragment_cities) {
    @Inject
    lateinit var vmFactory: VMFactory
    private val viewModel: CitiesVM by viewModels { vmFactory }
    private val viewBinding by viewBinding(FragmentCitiesBinding::bind)

    private var citiesAdapter: CitiesAdapter? = null

    override fun onAttach(context: Context) {
        (activity?.application as App).daggerAppComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        citiesAdapter = CitiesAdapter {
            // handle navigation
            findNavController().navigate(
                CitiesFragmentDirections.actionCitiesFragmentToMapFragment(cityName = it.city.name)
            )
        }

        with(viewBinding) {
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = citiesAdapter
        }

        lifecycleScope.launchWhenResumed {
            viewModel.citiesDistances.collect { result ->
                when (result) {
                    is RepoResult.Success -> {
                        citiesAdapter?.submitList(result.cities)
                    }
                    is RepoResult.Error -> {
                        Toast.makeText(context, result.msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        citiesAdapter = null
    }
}