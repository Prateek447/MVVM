package com.example.mycodebook.mvvm.ui.home.quotes

import com.example.mycodebook.mvvm.R
import com.example.mycodebook.mvvm.data.db.entites.Quotes
import com.example.mycodebook.mvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote : Quotes
) : BindableItem<ItemQuoteBinding>(){
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {

        viewBinding.setQuote(quote)
    }

    override fun getLayout() = R.layout.item_quote


}