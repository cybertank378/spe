package com.spe.spetest.ui.char

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.spe.spetest.data.repo.CharsRepo

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:43 PM.
 * ===================================================
 * Package              : com.spe.spetest.ui.char.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
class CharsViewModel @ViewModelInject constructor(
    private val repository: CharsRepo
) : ViewModel() {

    val chars = repository.getChars()
}