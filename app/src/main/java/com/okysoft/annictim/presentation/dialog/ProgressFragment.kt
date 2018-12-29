package com.okysoft.annictim.presentation.dialog


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R

class ProgressFragment : Fragment() {

    companion object {
        val TAG = ProgressFragment::class.java.simpleName
        fun newInstance() = ProgressFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }


}
