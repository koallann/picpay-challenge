package com.picpay.desafio.android.user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.ApplicationComponent
import com.picpay.desafio.android.R
import com.picpay.desafio.android.common.di.ViewModelFactory
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.user.di.UserComponent
import com.picpay.desafio.android.user.presentation.UserListAdapter
import com.picpay.desafio.android.user.presentation.UserViewModel
import com.picpay.desafio.android.user.presentation.UserViewModelEvent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val activityComponent: UserComponent by lazy {
        (applicationContext as ApplicationComponent).appComponent.userComponent.create()
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        observeViewModel()

        // load users on first activity instance
        if (savedInstanceState == null) viewModel.onLoadUsers()
    }

    private fun setupView() {
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = UserListAdapter()
        }
    }

    private fun observeViewModel() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is UserViewModelEvent.ErrorLoadingUsers -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerView.isVisible = false
                    Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.users.observe(this) { users ->
            binding.progressBar.isVisible = false
            adapter.users = users
        }
    }

}
