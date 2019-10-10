package ir.sdrv.mobilletsample.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ir.sdrv.mobilletsample.R
import ir.sdrv.mobilletsample.databinding.SingleUserFragmentBinding
import ir.sdrv.mobilletsample.presentation.viewmodel.SingleUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingleUserFragment : Fragment() {

    private val singleUserViewModel: SingleUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SingleUserFragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.single_user_fragment, container, false)
        binding.singleUserViewModel = singleUserViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val username: String = SingleUserFragmentArgs.fromBundle(it) .username
            singleUserViewModel.getUserInfoByUsername(username)
        }
    }

}
