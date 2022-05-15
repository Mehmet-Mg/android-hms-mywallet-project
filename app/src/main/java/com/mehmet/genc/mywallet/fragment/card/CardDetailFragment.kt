package com.mehmet.genc.mywallet.fragment.card

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mehmet.genc.mywallet.R
import com.mehmet.genc.mywallet.databinding.FragmentCardDetailBinding
import com.mehmet.genc.mywallet.entity.Card
import com.mehmet.genc.mywallet.viewmodel.CardViewModel

class CardDetailFragment : Fragment() {
    private lateinit var binding: FragmentCardDetailBinding
    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_detail, container, false)

        binding.cardDetailFragment = this
        val bundleData: CardDetailFragmentArgs by navArgs()
        val mCard = bundleData.card

        binding.cardObject = mCard

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CardViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updateCard(cardId: Int, cardName: String, cardNumber: String, cardOrganization: String, cardExpieryDate: String) {
        if(binding.editTextCardNumber.length() == 16) {
            viewModel.updateCard(Card(cardId, cardName,cardNumber,cardOrganization, cardExpieryDate))
            Toast.makeText(context, "The Card Updated", Toast.LENGTH_SHORT).show()
        } else {
            binding.editTextCardNumber.error = "Card number must be 16 digit"
        }
    }
}