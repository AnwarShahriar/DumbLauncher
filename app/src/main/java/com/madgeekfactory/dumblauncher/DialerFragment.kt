package com.madgeekfactory.dumblauncher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dialer.call
import kotlinx.android.synthetic.main.fragment_dialer.eight
import kotlinx.android.synthetic.main.fragment_dialer.five
import kotlinx.android.synthetic.main.fragment_dialer.four
import kotlinx.android.synthetic.main.fragment_dialer.nine
import kotlinx.android.synthetic.main.fragment_dialer.numberDisplay
import kotlinx.android.synthetic.main.fragment_dialer.one
import kotlinx.android.synthetic.main.fragment_dialer.plus
import kotlinx.android.synthetic.main.fragment_dialer.pound
import kotlinx.android.synthetic.main.fragment_dialer.save
import kotlinx.android.synthetic.main.fragment_dialer.seven
import kotlinx.android.synthetic.main.fragment_dialer.six
import kotlinx.android.synthetic.main.fragment_dialer.star
import kotlinx.android.synthetic.main.fragment_dialer.three
import kotlinx.android.synthetic.main.fragment_dialer.two
import kotlinx.android.synthetic.main.fragment_dialer.zero

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DialerFragment : Fragment(), OnClickListener {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dialer, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    zero.setOnClickListener(this)
    one.setOnClickListener(this)
    two.setOnClickListener(this)
    three.setOnClickListener(this)
    four.setOnClickListener(this)
    five.setOnClickListener(this)
    six.setOnClickListener(this)
    seven.setOnClickListener(this)
    eight.setOnClickListener(this)
    nine.setOnClickListener(this)
    star.setOnClickListener(this)
    pound.setOnClickListener(this)
    plus.setOnClickListener(this)
    call.setOnClickListener(this)
    save.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    var digit = when (v?.id) {
      R.id.zero -> "0"
      R.id.one -> "1"
      R.id.two -> "2"
      R.id.three -> "3"
      R.id.four -> "4"
      R.id.five -> "5"
      R.id.six -> "6"
      R.id.seven -> "7"
      R.id.eight -> "8"
      R.id.nine -> "9"
      R.id.star -> "*"
      R.id.pound -> "#"
      R.id.plus -> "+"
      else -> ""
    }
    numberDisplay.text = numberDisplay.text.toString() + digit
  }
}
