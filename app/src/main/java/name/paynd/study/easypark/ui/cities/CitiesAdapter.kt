package name.paynd.study.easypark.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import name.paynd.study.easypark.api.CityDistance
import name.paynd.study.easypark.databinding.ItemCityBinding

class CitiesAdapter(private val openMapClickListener: (CityDistance) -> Unit) :
    ListAdapter<CityDistance, CitiesAdapter.ViewHolder>(ClientItemDiffCallback()) {

    inner class ViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityDistance: CityDistance) {
            with(binding) {
                tvCityDistance.text = "${cityDistance.distance}"
                tvCityName.text = cityDistance.city.name
                root.setOnClickListener {
                    openMapClickListener.invoke(cityDistance)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


private class ClientItemDiffCallback : DiffUtil.ItemCallback<CityDistance>() {
    override fun areItemsTheSame(oldItem: CityDistance, newItem: CityDistance): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CityDistance, newItem: CityDistance): Boolean {
        return oldItem.city.name == newItem.city.name
    }
}