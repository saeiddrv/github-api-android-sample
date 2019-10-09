package ir.sdrv.mobilletsample.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.sdrv.mobilletsample.R
import ir.sdrv.mobilletsample.presentation.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersListFragment : Fragment() {

    private val usersListViewModel: UsersListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_list_fragment, container, false)
    }

}
