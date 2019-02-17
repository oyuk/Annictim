package com.okysoft.annictim.presentation.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.okysoft.annictim.R


class PersonFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    companion object {
        val TAG = PersonFragment::class.java.simpleName
        const val PERSON_ID = "PERSON_ID"

        fun newInstance(person_id: Int): PersonFragment = PersonFragment().apply {
            val args = Bundle().apply {
                putInt(PERSON_ID, person_id)
            }
            arguments = args
        }

    }

}
