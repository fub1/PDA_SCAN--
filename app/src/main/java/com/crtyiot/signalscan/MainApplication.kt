package com.crtyiot.signalscan


// Build application container after define Data Layer
// Build application container step 2:
// https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository#4

// INFO: Finish this step "must" define app name in the AndroidManifest.xml
// android:name=".ScanDataApplicationApplication"
// INFO: run the app to test the Data Layer & app container

// step 2.1: import "app.Application"
import android.app.Application

// step 2.2: import "data.AppContainer & data.AppDataContainer" from the data package


import com.crtyiot.signalscan.data.AppContainer
import com.crtyiot.signalscan.data.AppDataContainer

// step 2.3: class of xxxApplication:
class MainApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
