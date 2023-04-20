package com.sergio.appdesdecero.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.databinding.FragmentOptionsBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }
}