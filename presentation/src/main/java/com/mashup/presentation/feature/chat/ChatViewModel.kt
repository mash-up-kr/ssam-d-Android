package com.mashup.presentation.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mashup.domain.usecase.chat.GetChatRoomsUseCase
import com.mashup.presentation.feature.chat.model.RoomUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatRooms: GetChatRoomsUseCase
) : ViewModel() {

    private val _pagingData: Flow<PagingData<RoomUiModel>> =
        getChatRooms.execute(Unit).cachedIn(viewModelScope).map { pagingData ->
            pagingData.map {room ->RoomUiModel.fromDomainModel(room) }
        }
    val pagingData = _pagingData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = PagingData.empty()
    )

    sealed class ChatRoomUiState {
        object Loading : ChatRoomUiState()
        data class Success(val rooms: PagingData<RoomUiModel>) : ChatRoomUiState()
        data class Failure(val message: String) : ChatRoomUiState()
    }
}