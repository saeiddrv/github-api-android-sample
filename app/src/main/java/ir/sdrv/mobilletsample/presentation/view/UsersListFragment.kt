package ir.sdrv.mobilletsample.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.sdrv.mobilletsample.R
import ir.sdrv.mobilletsample.data.remote.api.base.Status
import ir.sdrv.mobilletsample.databinding.UsersListFragmentBinding
import ir.sdrv.mobilletsample.presentation.datasource.UsersListAdapter
import ir.sdrv.mobilletsample.presentation.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersListFragment : Fragment() {

    private val usersListViewModel: UsersListViewModel by viewModel()
    private lateinit var itemViewer: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: UsersListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.users_list_fragment, container, false)

        binding.usersListViewModel = usersListViewModel

        itemViewer = binding.root.findViewById(R.id.itemViewer)
        initAdapterAndObserve()

        return binding.root
    }

    private fun initAdapterAndObserve() {
        val usersListAdapter = UsersListAdapter()

        itemViewer.layoutManager = LinearLayoutManager(context)
        itemViewer.adapter = usersListAdapter

        Transformations.switchMap(usersListViewModel.dataSource) { dataSource -> dataSource.loadStateLiveData }
            .observe(this, Observer {
                when(it) {
                    Status.LOADING -> usersListViewModel.isWaiting.set(true)
                    Status.SUCCESS -> usersListViewModel.isWaiting.set(false)
                    Status.EMPTY -> showMessage(R.string.msg_users_list_is_empty)
                    else -> {
                        showMessage(R.string.msg_fetch_users_list_has_error)
                    }
                }
            })

        usersListViewModel.usersLiveData.observe(this, Observer {
            usersListAdapter.submitList(it)
        })
    }

    private fun showMessage(textResourceId: Int) {
        usersListViewModel.isWaiting.set(false)
        Toast.makeText(context, textResourceId, Toast.LENGTH_LONG).show()
    }
}
