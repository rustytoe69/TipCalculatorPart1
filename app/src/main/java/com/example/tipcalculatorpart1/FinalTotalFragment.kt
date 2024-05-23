package com.example.tipcalculatorpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tipcalculatorpart1.databinding.FragmentFinalTotalBinding
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import kotlin.math.roundToInt


class FinalTotalFragment : Fragment() {
    private var _binding: FragmentFinalTotalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFinalTotalBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val args = FinalTotalFragmentArgs.fromBundle(requireArguments())

        binding.subtotalPlusTipAmount.text="$%.2f".format(args.finalTotalArg.toDouble())
        binding.eachPersonOwesAmount.text="$%.2f".format((args.finalTotalArg.toDouble())/(args.numOfGuestsArg))
        binding.roundUpButton.setOnClickListener {
            binding.eachPersonOwesAmount.text="$%.2f".format(((args.finalTotalArg)/(args.numOfGuestsArg)).roundToInt().toDouble())
        }
        return rootView
    }
}