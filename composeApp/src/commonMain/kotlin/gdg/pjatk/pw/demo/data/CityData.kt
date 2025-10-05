package gdg.pjatk.pw.demo.data

data class CitySpec(
    val id: String,
    val name: String,
    val lon: Double,
    val lat: Double,
    val zoom: Double = 11.0
)

data class CountrySpec(
    val code: String,
    val name: String,
    val lon: Double,
    val lat: Double,
    val zoom: Double = 5.0,
    val cities: List<CitySpec>
)

val Countries: Map<String, CountrySpec> = listOf(
    CountrySpec(
        code = "PL",
        name = "Poland",
        lon = 19.1451, lat = 51.9194, zoom = 5.0,
        cities = listOf(
            CitySpec("krakow", "Kraków", 19.9445, 50.0497, 12.0),
            CitySpec("warsaw", "Warszawa", 21.0122, 52.2297, 12.0),
            CitySpec("gdansk", "Gdańsk", 18.6466, 54.3520, 12.0)
        )
    ),
    CountrySpec(
        code = "FR",
        name = "France",
        lon = 2.2137, lat = 46.2276, zoom = 5.0,
        cities = listOf(
            CitySpec("paris", "Paris", 2.3522, 48.8566, 12.0),
            CitySpec("lyon", "Lyon", 4.8357, 45.7640, 12.0),
            CitySpec("nice", "Nice", 7.2619, 43.7102, 12.0)
        )
    ),
    CountrySpec(
        code = "DE",
        name = "Germany",
        lon = 10.4515, lat = 51.1657, zoom = 5.0,
        cities = listOf(
            CitySpec("berlin", "Berlin", 13.4050, 52.5200, 12.0),
            CitySpec("munich", "Munich", 11.5820, 48.1351, 12.0)
        )
    ),
    CountrySpec(
        code = "IT",
        name = "Italy",
        lon = 12.5674, lat = 41.8719, zoom = 5.1,
        cities = listOf(
            CitySpec("rome", "Rome", 12.4964, 41.9028, 12.0),
            CitySpec("milan", "Milan", 9.1900, 45.4642, 12.0)
        )
    )
).associateBy { it.code }
