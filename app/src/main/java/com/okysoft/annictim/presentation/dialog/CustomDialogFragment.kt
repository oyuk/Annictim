package com.okysoft.annictim.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

class CustomDialogFragment: DialogFragment() {

    interface Listener {
        fun positiveAction()
        fun negativeAction()
    }

    private var listener: Listener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString(TITLE)
        val message = arguments?.getString(MESSAGE)
        val positiveButtonTitle = arguments?.getString(POSITIVE_BUTTON_TITLE)
        val negativeButtonTitle = arguments?.getString(NEGATIVE_BUTTON_TITLE)
        val builder = AlertDialog.Builder(activity)
        title?.let {
            builder.setTitle(it)
        }
        message?.let {
            builder.setMessage(it)
        }
        positiveButtonTitle?.let {
            builder.setPositiveButton(it) { _, _ ->
                listener?.positiveAction()
            }
        }
        negativeButtonTitle?.let {
            builder.setNegativeButton(it) { _, _ ->
                listener?.negativeAction()
            }
        }
        return builder.create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parentFragment?.let {
            if (it is CustomDialogFragment.Listener) {
                listener = it
            }
            return
        }
        if (context is CustomDialogFragment.Listener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        private val TAG = CustomDialogFragment::class.java.name
        private val TITLE = "title"
        private val MESSAGE = "message"
        private val POSITIVE_BUTTON_TITLE = "positiveButtonTitle"
        private val NEGATIVE_BUTTON_TITLE = "negativeButtonTitle"

        @JvmStatic
        private fun newInstance(bundle: Bundle): CustomDialogFragment {
            return CustomDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    class Builder {
        private var title: String? = null
        private var message: String? = null
        private var positiveButtonTitle: String? = null
        private var negativeButtonTitle: String? = null

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun positiveButtonTitle(title: String): Builder {
            this.positiveButtonTitle = title
            return this
        }

        fun negativeButtonTitle(title: String): Builder {
            this.negativeButtonTitle = title
            return this
        }

        fun build(): CustomDialogFragment {
            return CustomDialogFragment.newInstance(createBundle())
        }

        private fun createBundle(): Bundle {
            return Bundle().apply {
                putString(TITLE, title)
                putString(MESSAGE, message)
                putString(POSITIVE_BUTTON_TITLE, positiveButtonTitle)
                putString(NEGATIVE_BUTTON_TITLE, negativeButtonTitle)
            }
        }

        fun show(activity: FragmentActivity) {
            build().show(activity.supportFragmentManager, TAG)
        }

        fun show(fragment: Fragment) {
            val f = build()
            f.show(fragment.childFragmentManager, TAG)
        }

    }

}
