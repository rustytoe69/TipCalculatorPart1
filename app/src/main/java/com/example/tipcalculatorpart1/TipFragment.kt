package com.example.tipcalculatorpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.tipcalculatorpart1.databinding.FragmentTipBinding
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController


class TipFragment : Fragment() {
    private var _binding: FragmentTipBinding? = null
    private val binding get() = _binding!!

    var subtotal = 100
    var numOfGuests: Int = 100
    var tipPercent: Int = 0
    var finalTotal: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTipBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //p2:
        val args = TipFragmentArgs.fromBundle(requireArguments())
        subtotal=args.subtotalArg
        binding.subtotalDollarAmount.text="$$subtotal.00"
        if(args.isTipDefaultArg){
            binding.button18p.isChecked=true
            radioButtonFunctions(18)
        }

        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()

        binding.nextButton.setOnClickListener{
            val passIn = TipFragmentDirections.actionTipFragmentToFinalTotalFragment(finalTotal.toFloat(),numOfGuests)
            rootView.findNavController().navigate(passIn)
        }

        return rootView
    }

    fun setUpRadioButtons() {
        binding.button10p.setOnClickListener { view ->
            radioButtonFunctions(10)
        }

        binding.button15p.setOnClickListener { view ->
            radioButtonFunctions(15)
        }

        binding.button18p.setOnClickListener { view ->
            radioButtonFunctions(18)
        }

        binding.button25p.setOnClickListener { view ->
            radioButtonFunctions(25)
        }
    }

    fun radioButtonFunctions(percent: Int) {
        tipPercent = percent
        setTipAndTotalViews()
        binding.seekBar.progress = tipPercent
    }


    fun setUpTipSeekBar() {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                newProgValue: Int,
                changedByUser: Boolean
            ) {

                tipPercent = seekBar.progress
                setTipAndTotalViews()
                if (tipPercent == 10) {
                    binding.button10p.isChecked = true
                } else if (tipPercent == 15) {
                    binding.button15p.isChecked = true
                } else if (tipPercent == 18) {
                    binding.button18p.isChecked = true
                } else if (tipPercent == 25) {
                    binding.button25p.isChecked = true
                } else binding.radioGroup.clearCheck()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    fun setUpNumOfGuestsSpinner() {
        val guestNumOptionsArrayAdapter = ArrayAdapter.createFromResource(
            requireActivity(), R.array.guest_num_options, android.R.layout.simple_spinner_item
        )
        guestNumOptionsArrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        binding.numOfGuestsSpinner.adapter = guestNumOptionsArrayAdapter


        binding.numOfGuestsSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    viewPosition: Int,
                    rowId: Long
                ) {
                    numOfGuests = adapterView.getItemAtPosition(viewPosition).toString().toInt()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {
                }
            }
    }





    fun setTipAndTotalViews() {
        finalTotal = subtotal + (subtotal * (tipPercent * 0.01))

        binding.tipAmountEdit.text = "$tipPercent%"
        binding.totalWTipAmount.text = "$%.2f".format(finalTotal)
    }
}