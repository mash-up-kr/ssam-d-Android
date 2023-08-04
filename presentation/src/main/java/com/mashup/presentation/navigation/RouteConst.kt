package com.mashup.presentation.navigation

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */

const val HOME_GRAPH_PATTERN = "home_graph"
const val HOME_ROUTE = "home_graph/home"
const val GUIDE_ROUTE = "home_graph/guide"
const val SUBSCRIBE_KEYWORD_ROUTE = "home_graph/subscribe_keyword"

const val SIGNAL_GRAPH_PATTERN = "signal_graph"
const val SIGNAL_CONTENT_ROUTE = "signal_graph/content"
const val SIGNAL_KEYWORD_ROUTE = "signal_graph/keyword"
const val SIGNAL_COMPLETE_ROUTE = "signal_graph/complete"

const val CHAT_ROOM_GRAPH_PATTERN = "chat_room_graph"
const val CHAT_ROOM_ROUTE = "chat_room_graph/rooms"
const val CHAT_ROOM_DETAIL_ROUTE = "chat_room_graph/rooms/{roomId}"
const val CHAT_DETAIL_ROUTE = "chat_room_graph/rooms/{roomId}/chats/{chatId}"
const val CHAT_REPLY_ROUTE = "chat_room_graph/rooms/{roomId}/chats"
const val CHAT_REPORT_ROUTE = "chat_room_graph/report"

const val PROFILE_GRAPH_PATTERN = "profile_graph"
const val PROFILE_ROUTE = "profile_graph/profile"
const val TERMS_OF_SERVICE_ROUTE = "profile_graph/{userId}/toc"
const val PRIVACY_POLICY_ROUTE = "profile_graph/{userId}/policy"
const val WITHDRAWAL_ROUTE = "profile_graph/{userId}/withdrawal"

const val RECEIVED_SIGNAL_GRAPH = "chat_graph"
const val RECEIVED_SIGNAL_DETAIL_ROUTE = "chat_graph/signal/{signalId}"
const val RECEIVED_SIGNAL_REPLY_ROUTE = "chat_graph/signal/{signalId}/reply"
const val RECEIVED_SIGNAL_REPORT_ROUTE = "chat_graph/report"

const val CRASHES_GRAPH_PATTERN = "crashes_graph"
const val CRASHES_ROUTE = "crashes_graph/crashes"