package `in`.cropdata.machine_test.yogesg_dhatrak.utils

import `in`.cropdata.machine_test.yogesg_dhatrak.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment


class CustomProgressDialogue : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogTheme)

        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_custom_progress, container)
    }


    companion object {
        const val TAG: String = "CustomProgressDialogue"
    }
}