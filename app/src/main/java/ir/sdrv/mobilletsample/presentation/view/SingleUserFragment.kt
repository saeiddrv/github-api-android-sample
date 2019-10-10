package ir.sdrv.mobilletsample.presentation.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.sdrv.mobilletsample.R
import ir.sdrv.mobilletsample.presentation.viewmodel.SingleUserViewModel

class SingleUserFragment : Fragment() {

    private lateinit var viewModel: SingleUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.single_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SingleUserViewModel::class.java)

        arguments?.let {
            val username: String = SingleUserFragmentArgs.fromBundle(it) .username
        }
    }

}
