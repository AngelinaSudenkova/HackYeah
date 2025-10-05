package gdg.pjatk.pw.demo

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.gson.JsonPrimitive
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.layers.properties.generated.ProjectionName
import com.mapbox.maps.extension.style.projection.generated.Projection
import com.mapbox.maps.extension.style.projection.generated.projection
import com.mapbox.maps.extension.style.projection.generated.setProjection
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.*

@Composable
actual fun CityMap(
    modifier: Modifier,
    centerLon: Double,
    centerLat: Double,
    zoom: Double,
    planet: Boolean,
    markers: List<MarkerSpec>,
    onMarkerTap: (id: String) -> Unit
) {
    val managerRef = androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf<PointAnnotationManager?>(null) }

    AndroidView(
        modifier = modifier,
        factory = { ctx: Context ->
            val mv = MapView(ctx)

            // Load style with initial projection
            mv.getMapboxMap().loadStyle(
                style(Style.STANDARD) {
                    projection(if (planet) ProjectionName.GLOBE else ProjectionName.MERCATOR)
                }
            ) {
                mv.getMapboxMap().setCamera(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(centerLon, centerLat))
                        .zoom(zoom)
                        .build()
                )

                val manager = mv.annotations.createPointAnnotationManager()
                managerRef.value = manager

                addMarkers(manager, markers)

                manager.addClickListener(object : OnPointAnnotationClickListener {
                    override fun onAnnotationClick(annotation: PointAnnotation): Boolean {
                        annotation.getData()?.asString?.let {
                            onMarkerTap(it)
                            return true
                        }
                        return false
                    }
                })
            }

            mv
        },
        update = { mv ->
            // ✅ Change projection at runtime using a Projection object
            mv.getMapboxMap().getStyle { st ->
                st.setProjection(
                    Projection(if (planet) ProjectionName.GLOBE else ProjectionName.MERCATOR)
                )
            }

            mv.getMapboxMap().setCamera(
                CameraOptions.Builder()
                    .center(Point.fromLngLat(centerLon, centerLat))
                    .zoom(zoom)
                    .build()
            )

            managerRef.value?.let { manager ->
                manager.deleteAll()
                addMarkers(manager, markers)
            }
        },
        onRelease = { mv ->
            managerRef.value?.deleteAll()
            managerRef.value = null
            mv.onStop()
            mv.onDestroy()
        }
    )
}

private fun addMarkers(manager: PointAnnotationManager, markers: List<MarkerSpec>) {
    for (m in markers) {
        val opts = PointAnnotationOptions()
            .withPoint(Point.fromLngLat(m.lon, m.lat))
            .withData(JsonPrimitive(m.id)) // store your String id directly
        manager.create(opts)
    }
}
