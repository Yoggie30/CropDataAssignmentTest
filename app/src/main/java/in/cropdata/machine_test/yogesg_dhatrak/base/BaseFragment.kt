package `in`.cropdata.machine_test.yogesg_dhatrak.base

import `in`.cropdata.machine_test.yogesg_dhatrak.R
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.CustomProgressDialogue
import `in`.cropdata.machine_test.yogesg_dhatrak.utils.CustomProgressManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder


open class BaseFragment : Fragment() {
    var isProgressVisible = false

    open fun hideProgress() {
        if (isProgressVisible) {
            CustomProgressManager.dialog.dismiss()
            isProgressVisible = false
        }
    }

    open fun showProgress() {
        if (!isProgressVisible) {
            CustomProgressManager.dialog.show(childFragmentManager, CustomProgressDialogue.TAG)
            isProgressVisible = true
        }
    }

    open fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    open fun showAlert(message: String) {
        activity?.let { it1 ->
            MaterialAlertDialogBuilder(it1)
                .setMessage(message)
                .setPositiveButton(resources.getString(R.string.label_ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }




}