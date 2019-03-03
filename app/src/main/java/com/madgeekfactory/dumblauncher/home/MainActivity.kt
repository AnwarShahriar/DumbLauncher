package com.madgeekfactory.dumblauncher.home

import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.madgeekfactory.dumblauncher.R
import com.madgeekfactory.dumblauncher.dialer.CallEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onBackPressed() {
    // do nothing
  }

  override fun onStart() {
    super.onStart()
    EventBus.getDefault()
        .register(this)
  }

  override fun onStop() {
    super.onStop()
    EventBus.getDefault()
        .unregister(this)
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  fun onCallStatusChanged(callEvent: CallEvent) {
    when (callEvent.state) {
      Call.STATE_RINGING -> openCallScreen(callEvent.call)
    }
  }

  private fun openCallScreen(call: Call?) {
    val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    val destinationId = navController.currentDestination?.id

    if (destinationId != R.id.callFragment) {
      val bundle = Bundle()
      bundle.putString("number", call?.details?.handle?.encodedSchemeSpecificPart)
      bundle.putBoolean("incoming", true)
      navController.navigate(R.id.action_global_callFragment, bundle)
    }
  }
}
