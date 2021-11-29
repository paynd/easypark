package name.paynd.study.easypark.location

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LocationProvider {
    val locationHotState: Flow<LocationState>
}