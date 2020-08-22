package com.learningAndroiddeve.androidcalenderview.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.learningAndroiddeve.androidcalenderview.R
import com.learningAndroiddeve.androidcalenderview.databinding.SplashFragmentBinding
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {

    private lateinit var splashFragmentBinding: SplashFragmentBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        splashFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment,container,false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = SplashScreenViewModelFactory(application)
        val viewModel = ViewModelProvider(this,viewModelFactory)
            .get(SplashScreenViewModel::class.java)


        splashFragmentBinding.viewModel = viewModel
        splashFragmentBinding.lifecycleOwner = this



        activityScope.launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashScreenFragment_to_reservationFragment)
        }

        return splashFragmentBinding.root
    }
    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

}