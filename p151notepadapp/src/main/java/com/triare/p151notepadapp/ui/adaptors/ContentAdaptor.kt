package com.triare.p151notepadapp.ui.adaptors

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.triare.p151notepadapp.R
import com.triare.p151notepadapp.ui.dvo.ContentDvo
import com.triare.p151notepadapp.ui.dvo.NoteDvo

class ContentAdaptor(
    //private val items: List<ContentDvo>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ContentAdaptor.ContentViewHolder>() {

    private var items: List<ContentDvo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content, parent, false)
        return ContentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun submitContentList(contentList: List<ContentDvo>) {
        val oldList = items
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            ContentItemDiffCallback(
                oldList,
                contentList
            )
        )
        items = contentList
        diffResult.dispatchUpdatesTo(this)
    }

    class ContentItemDiffCallback(
        private val oldContentList: List<ContentDvo>,
        private val newContentList: List<ContentDvo>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            Log.d("Adaptor123", oldContentList.size.toString())
            return oldContentList.size
        }

        override fun getNewListSize(): Int {
            Log.d("Adaptor123", newContentList.size.toString())
            return newContentList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return (oldContentList[oldItemPosition].contentId == newContentList[oldItemPosition].contentId)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldContentList[oldItemPosition].equals(newContentList[oldItemPosition])
        }
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val title = itemView.findViewById<TextView>(R.id.title_item)
        private val dataTime = itemView.findViewById<TextView>(R.id.time_when)
        private val fractions = itemView.findViewById<TextView>(R.id.fractions)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: ContentDvo) {
            title.text = data.title
            dataTime.text = data.dataTime
            fractions.text = data.fractions

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, contentId = items[position].contentId)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, contentId: Long)
    }

}