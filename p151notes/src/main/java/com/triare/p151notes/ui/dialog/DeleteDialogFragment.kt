package com.triare.p151notes.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class DeleteDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener { _, witch ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to witch))
        }
        return  AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle("Delete notes?")
            .setMessage("Are you really want to delete notes?")
            .setPositiveButton("Just do it", listener)
            .setNegativeButton("No", listener)
            .create()

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
    companion object{
        @JvmStatic val TAG = DeleteDialogFragment::class.java.simpleName
        @JvmStatic val REQUEST_KEY = "$TAG:defaultRequestKey"
        @JvmStatic val KEY_RESPONSE = "RESPONSE"
    }
}