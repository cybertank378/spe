package com.spe.spetest.ui.char

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.spe.spetest.data.entities.Chars
import com.spe.spetest.databinding.ItemCharsBinding

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:40 PM.
 * ===================================================
 * Package              : com.spe.spetest.ui.char.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
class CharsAdapter (private val listener: CharsItemListener) : RecyclerView.Adapter<CharsViewHolder>() {

    interface CharsItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<Chars>()

    fun setItems(items: ArrayList<Chars>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharsViewHolder {
        val binding: ItemCharsBinding = ItemCharsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharsViewHolder, position: Int) = holder.bind(items[position])
}

class CharsViewHolder(private val itemBinding: ItemCharsBinding, private val listener: CharsAdapter.CharsItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var chars: Chars

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Chars) {
        this.chars = item
        itemBinding.name.text = item.name
        itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""
        Glide.with(itemBinding.root)
            .load(item.image)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(chars.id)
    }
}