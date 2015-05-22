package net.tuxv.miwaykotlin.utils

import retrofit.RestAdapter
import kotlin.properties.Delegates

class BusTimesService() {
    val url = "https://tactical-unison-651.appspot.com/_ah/api/busTimes/v1/"
    public var busTimesApi : BusTimesApi by Delegates.notNull()

    init {
        val restAdapter = RestAdapter.Builder()
            .setEndpoint(url)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()

        busTimesApi = restAdapter.create(javaClass<BusTimesApi>())
    }
}
