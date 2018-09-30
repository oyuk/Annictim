package com.okysoft.annictim.Presentation.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity

class CustomDialogFragment: DialogFragment() {

    interface Listener {
        fun positiveAction()
        fun negativeAction()
    }

    var listener: Listener? = null


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

    class Builder(private val activity: AppCompatActivity) {
        private var title: String? = null
        private var message: String? = null
        private var positiveButtonTitle: String? = null
        private var negativeButtonTitle: String? = null
        private var listener: CustomDialogFragment.Listener? = null

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

        fun listener(listener: Listener): Builder {
            this.listener = listener
            return this
        }

        private fun create(): CustomDialogFragment {
            val bundle = Bundle().apply {
                putString(TITLE, title)
                putString(MESSAGE, message)
                putString(POSITIVE_BUTTON_TITLE, positiveButtonTitle)
                putString(NEGATIVE_BUTTON_TITLE, negativeButtonTitle)
            }
            val fragment = CustomDialogFragment.newInstance(bundle)
            fragment.listener = listener
            return fragment
        }

        fun show() {
            show(TAG)
        }

        fun show(tag: String) {
            create().show(activity.supportFragmentManager, tag)
        }

    }

}
