package com.mehmet.genc.mywallet.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mehmet.genc.mywallet.databinding.CardViewHolderBinding
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.fragment.card.CardFragmentDirections
import com.mehmet.genc.mywallet.viewmodel.CardViewModel

class CardAdapter(var context: Context, var cardList: List<Card>, var viewModel: CardViewModel)
    : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(binding: CardViewHolderBinding)
        : RecyclerView.ViewHolder(binding.root) {
        var binding: CardViewHolderBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = CardViewHolderBinding.inflate(layoutInflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        val mHolder = holder.binding
        mHolder.card = card



        mHolder.deleteButton.setOnClickListener {
            Snackbar.make(it, "Are you sure?", Snackbar.LENGTH_SHORT)
                .setAction("Yes") {
                    viewModel.deleteCard(card)
                }.show()
        }

        mHolder.cardViewHolder.setOnClickListener {
            val action = CardFragmentDirections.actionCardFragmentToCardDetailFragment(card)
            Navigation.findNavController(it).navigate(action)
        }

        mHolder.imageViewCopy.setOnClickListener {
            val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData =  ClipData.newPlainText("Card Number", card.number)
            clipBoard.setPrimaryClip(clipData)
            clipData.description
            Toast.makeText(context, "Card Number Copied.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = cardList.size
}