package com.mashup.presentation.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase
): ViewModel() {

    fun getChats(id: Int) {
        viewModelScope.launch {
            getChatsUseCase.execute(id)
                .onSuccess {  }
                .onFailure {  }
        }
    }
}