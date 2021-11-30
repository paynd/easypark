package name.paynd.study.easypark.location

import android.content.Context
import android.location.Location
import android.util.Log
import com.birjuvachhani.locus.Locus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LocationProviderImpl @Inject constructor(
    private val context: Context,
) : LocationProvider {
    //    init {
//        Locus
//            .configure {
//                request {
//                    fastestInterval = 1000
//                    interval = 1000
//                    maxWaitTime = 2000
//                }
//            }
//
//    }

    @ExperimentalCoroutinesApi
    override val locationState = callbackFlow {
        Locus.getCurrentLocation(context) { result ->
            when {
                result.location != null -> {
                    trySend(
                        LocationState.Success(result.location as Location)
                    )

                }
                else -> {
                    trySend(
                        LocationState.Error(
                            result.error?.message ?: "Unknown error my friend, really unknown!"
                        )
                    )

                }
            }
        }

        //todo: we need more elegant solution, use mapbox location provider instead of locus?
        awaitClose {}
    }
}

sealed class LocationState {
    data class Success(
        val location: Location
    ) : LocationState()

    data class Error(
        val message: String
    ) : LocationState()
}