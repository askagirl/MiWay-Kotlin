package net.tuxv.miwaykotlin

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by yasith on 26/07/15.
 */

class MiwayApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
