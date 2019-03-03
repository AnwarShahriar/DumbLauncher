package com.madgeekfactory.dumblauncher.dialer

import android.net.Uri
import android.os.Bundle
import android.telecom.Call
import android.telecom.TelecomManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.madgeekfactory.dumblauncher.R
import kotlinx.android.synthetic.main.fragment_call.displayName
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CallFragment : Fragment() {
  lateinit var number: String
  var incomingCall  = false

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_call, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    number = arguments?.get("number") as String
    incomingCall = arguments?.getBoolean("incoming", false)!!
    requestToEstablishCall(number)
  }

  private fun requestToEstablishCall(number: String) {
    val uri = Uri.fromParts("tel", number, null)
    val extras = Bundle()
    extras.putBoolean(TelecomManager.EXTRA_START_CALL_WITH_SPEAKERPHONE, true)
    val telecomManager = context?.getSystemService(TelecomManager::class.java) as TelecomManager

    if (incomingCall) {
      updateDisplayName()
    } else {
      // todo: check for permission
      telecomManager.placeCall(uri, extras)
    }
  }

  override fun onResume() {
    super.onResume()
    EventBus.getDefault().register(this)
  }

  override fun onPause() {
    super.onPause()
    EventBus.getDefault().unregister(this)
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  fun onCallStatusChanged(callEvent: CallEvent) {
    when (callEvent.state) {
      Call.STATE_DIALING -> updateDisplayName()
      Call.STATE_DISCONNECTED -> goBackToDialer()
    }
  }

  private fun goBackToDialer() {
    NavHostFragment.findNavController(this)
        .navigate(R.id.action_callFragment_to_dialerFragment)
  }

  private fun updateDisplayName() {
    displayName.text = number
  }
}
