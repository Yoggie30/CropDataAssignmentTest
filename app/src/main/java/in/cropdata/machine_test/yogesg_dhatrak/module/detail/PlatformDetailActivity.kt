package `in`.cropdata.machine_test.yogesg_dhatrak.module.detail

import `in`.cropdata.machine_test.yogesg_dhatrak.R
import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformDetailsModel
import `in`.cropdata.machine_test.yogesg_dhatrak.databinding.ActivityPlatformDetailBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PlatformDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPlatformDetailBinding =
            ActivityPlatformDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpComponents(binding)
    }

    private fun setUpComponents(binding: ActivityPlatformDetailBinding) {
        val gson = Gson()
        val platformDetailsModel = gson.fromJson<PlatformDetailsModel>(
            intent.getStringExtra("platformData"),
            PlatformDetailsModel::class.java
        )
        Timber.e("platformDetailsModel-->$platformDetailsModel")
        binding.apply {
            titleTextView.text = platformDetailsModel.name
            descriptionTextView.text = platformDetailsModel.description
            Glide.with(applicationContext)
                .load(platformDetailsModel.iconURL)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .transform(CircleCrop())
                .into(imageView)
        }
    }
}