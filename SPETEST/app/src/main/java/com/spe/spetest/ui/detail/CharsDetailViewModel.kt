package com.spe.spetest.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.spe.spetest.data.entities.Chars
import com.spe.spetest.data.repo.CharsRepo
import com.spe.spetest.utils.Resource

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 2:04 PM.
 * ===================================================
 * Package              : com.spe.spetest.ui.detail.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
class CharsDetailViewModel @ViewModelInject constructor(
    private val repository: CharsRepo
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _chars = _id.switchMap { id ->
        repository.getChar(id)
    }
    val chars: LiveData<Resource<Chars>> = _chars


    fun start(id: Int) {
        _id.value = id
    }

}