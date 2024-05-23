package com.example.tipcalculatorpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tipcalculatorpart1.databinding.FragmentSubtotalBinding
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController


class SubtotalFragment : Fragment() {
    private var _binding: FragmentSubtotalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSubtotalBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.nextPageButton.setOnClickListener {
            val isDefaultTip: Boolean = binding.defaultTipSwitch.isChecked
            if (binding.editTextNumber.text.toString()!="") {
                val subtotal: Int = binding.editTextNumber.text.toString().toInt()
                val passIn = SubtotalFragmentDirections.actionSubtotalFragmentToTipFragment(isDefaultTip,subtotal)
                rootView.findNavController().navigate(passIn)
            }
        }


        return rootView
    }
}