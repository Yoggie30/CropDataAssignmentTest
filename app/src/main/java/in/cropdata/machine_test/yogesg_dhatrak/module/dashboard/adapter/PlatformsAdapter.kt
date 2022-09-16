package `in`.cropdata.machine_test.yogesg_dhatrak.module.dashboard.adapter

import `in`.cropdata.machine_test.yogesg_dhatrak.R
import `in`.cropdata.machine_test.yogesg_dhatrak.data.entities.PlatformDetailsModel
import `in`.cropdata.machine_test.yogesg_dhatrak.databinding.LayoutItemPlatformBinding
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class PlatformsAdapter(
    private val listener: PlatformItemListener,
    private val appContext: Context
) :
    RecyclerView.Adapter<PlatformsViewHolder>() {

    interface PlatformItemListener {
        fun onClickedPlatform(platform: PlatformDetailsModel)
    }

    private val items = ArrayList<PlatformDetailsModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<PlatformDetailsModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformsViewHolder {
        val binding: LayoutItemPlatformBinding =
            LayoutItemPlatformBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlatformsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PlatformsViewHolder, position: Int) =
        holder.bind(items[position],appContext)
}

class PlatformsViewHolder(
    private val itemBinding: LayoutItemPlatformBinding,
    private val listener: PlatformsAdapter.PlatformItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var platform: PlatformDetailsModel

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: PlatformDetailsModel, appContext: Context) {
        this.platform = item
        itemBinding.apply {
            titleTextView.text = item.name
            descriptionTextView.text = item.description
            Glide.with(appContext)
                .load(item.iconURL)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .transform(CircleCrop())
                .into(itemBinding.imageView)

        }

    }

    override fun onClick(v: View?) {
        listener.onClickedPlatform(platform)
    }
}

