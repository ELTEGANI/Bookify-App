package com.learningAndroiddeve.androidcalenderview.allchalets

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import com.learningAndroiddeve.androidcalenderview.R
import com.learningAndroiddeve.androidcalenderview.adapter.ChaletAdapter
import com.learningAndroiddeve.androidcalenderview.databinding.AllChaletsFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AllFragments : Fragment() {

    private lateinit var allFragmentsViewModel: AllFragmentsViewModel
    private lateinit var allChaletsFragmentBinding: AllChaletsFragmentBinding
    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        allChaletsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.all_chalets_fragment,container,false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory = AllFragmentsViewModelFactory(application)
        allFragmentsViewModel = ViewModelProvider(this,viewModelFactory)
            .get(AllFragmentsViewModel::class.java)


        allChaletsFragmentBinding.viewModel = allFragmentsViewModel
        allChaletsFragmentBinding.lifecycleOwner = this

        (activity as AppCompatActivity).setSupportActionBar(allChaletsFragmentBinding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        val chaletAdapter = ChaletAdapter()
        allChaletsFragmentBinding.chaletsList.itemAnimator = DefaultItemAnimator()
        allChaletsFragmentBinding.chaletsList.adapter = chaletAdapter
        subscribeUi(chaletAdapter)

        return allChaletsFragmentBinding.root
    }

    @ExperimentalCoroutinesApi
    private fun subscribeUi(adapter: ChaletAdapter) {
        allFragmentsViewModel.allChalets.observe(viewLifecycleOwner) { allChalets ->
            adapter.submitList(allChalets)
        }
    }
}