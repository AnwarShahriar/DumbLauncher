package com.madgeekfactory.dumblauncher.dialer

import android.telecom.Call

data class CallEvent(val call: Call?, val state: Int)