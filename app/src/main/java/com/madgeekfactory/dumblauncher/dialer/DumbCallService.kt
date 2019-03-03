package com.madgeekfactory.dumblauncher.dialer

import android.telecom.Call
import android.telecom.InCallService
import org.greenrobot.eventbus.EventBus

class DumbCallService : InCallService() {
    override fun onCallAdded(call: Call?) {
        super.onCallAdded(call)
        call?.registerCallback(callCallback)
        callCallback.onStateChanged(call, call?.state ?: -1)
    }

    override fun onCallRemoved(call: Call?) {
        super.onCallRemoved(call)
        call?.unregisterCallback(callCallback)
    }

    private val callCallback = object : Call.Callback() {
        override fun onStateChanged(call: Call?, state: Int) {
            EventBus.getDefault().post(CallEvent(call, state))
        }
    }
}