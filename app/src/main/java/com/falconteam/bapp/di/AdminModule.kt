package com.falconteam.bapp.di

import com.falconteam.bapp.ui.admin.viewmodel.AdminHomeViewModel
import com.falconteam.bapp.ui.admin.viewmodel.DeleteUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adminModule = module {
    viewModel { AdminHomeViewModel() }
    viewModel { DeleteUserViewModel() }
}
