package name.paynd.study.easypark.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.*
import name.paynd.study.easypark.R
import name.paynd.study.easypark.api.City
import name.paynd.study.easypark.api.toPoint

object MapUtil {
    private const val ICON_SIZE = 128
    private const val FILL_COLOR = "#ee4e8b"
    private const val FILL_OPACITY = 0.4
    private const val ICON_RES = R.drawable.ic_location

    fun MapView.flyTo(city: City) {
        flyTo(city.toPoint())
    }

    fun MapView.flyTo(point: Point) {
        getMapboxMap().flyTo(
            CameraOptions
                .Builder()
                .zoom(10.0)
                .center(point)
                .build(),
            MapAnimationOptions.mapAnimationOptions {
                duration(2000)
            }
        )
    }

    fun MapView.createPointAnnotation(city: City) {
        this.createPointAnnotation(city.toPoint())
    }

    fun MapView.createPointAnnotation(point: Point) {
        bitmapFromDrawableRes(
            this.context,
            ICON_RES
        )?.let {
            val annotationApi = this.annotations
            val pointAnnotationManager = annotationApi.createPointAnnotationManager(this)
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(it)
            pointAnnotationManager.create(pointAnnotationOptions)
        }
    }


    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                ICON_SIZE, ICON_SIZE,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    fun MapView.createPolylineAnnotation(city: City) {
        val annotationApi = this.annotations
        val polylineAnnotationManager = annotationApi.createPolylineAnnotationManager(this)
        val points = city.points.map { it.toPoint() }

        // Set options for the resulting line layer.
        val polylineAnnotationOptions: PolylineAnnotationOptions = PolylineAnnotationOptions()
            .withPoints(points)
            .withLineColor(FILL_COLOR)
            .withLineWidth(5.0)


        polylineAnnotationManager.create(polylineAnnotationOptions)
    }

    fun MapView.createPolygonAnnotation(city: City) {
        val annotationApi = this.annotations
        val polygonAnnotationManager = annotationApi.createPolygonAnnotationManager(this)
        val points = listOf(city.points.map { it.toPoint() })
        val polygonAnnotationOptions: PolygonAnnotationOptions = PolygonAnnotationOptions()
            .withPoints(points)
            .withFillColor(FILL_COLOR)
            .withFillOpacity(FILL_OPACITY)
        polygonAnnotationManager.create(polygonAnnotationOptions)
    }

}