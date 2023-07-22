package com.mashup.presentation.navigation

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
sealed class KeyLinkNavigationRoute(val route: String) {
    object HomeGraph : KeyLinkNavigationRoute(HOME_GRAPH_PATTERN) {
        object HomeRoute : KeyLinkNavigationRoute(HOME_ROUTE)
        object SubscribeKeywordRoute : KeyLinkNavigationRoute(SUBSCRIBE_KEYWORD_ROUTE)
        object GuideRoute : KeyLinkNavigationRoute(GUIDE_ROUTE)
    }
    object SignalGraph : KeyLinkNavigationRoute(SIGNAL_GRAPH_PATTERN) {
        object SignalContentRoute : KeyLinkNavigationRoute(SIGNAL_CONTENT_ROUTE)
        object SignalKeywordRoute : KeyLinkNavigationRoute(SIGNAL_KEYWORD_ROUTE)
        object SignalCompleteRoute : KeyLinkNavigationRoute(SIGNAL_COMPLETE_ROUTE)
    }

    object ChatRoomGraph : KeyLinkNavigationRoute(CHAT_ROOM_GRAPH_PATTERN) {
        object ChatRoomRoute : KeyLinkNavigationRoute(CHAT_ROOM_ROUTE)
        object ChatRoomDetailRoute : KeyLinkNavigationRoute(CHAT_ROOM_DETAIL_ROUTE)
    }

    object ProfileGraph : KeyLinkNavigationRoute(PROFILE_GRAPH_PATTERN) {
        object ProfileRoute : KeyLinkNavigationRoute(PROFILE_ROUTE)
        object SendSignalRoute : KeyLinkNavigationRoute(SEND_SIGNAL_ROUTE)
        object TermsOfServiceRoute : KeyLinkNavigationRoute(TERMS_OF_SERVICE_ROUTE)
        object PrivacyPolicyRoute : KeyLinkNavigationRoute(PRIVACY_POLICY_ROUTE)
        object OpenSourceRoute : KeyLinkNavigationRoute(OPEN_SOURCE_ROUTE)
    }

    object ChatGraph : KeyLinkNavigationRoute(CHAT_GRAPH_PATTERN) {
        object ChatDetailRoute : KeyLinkNavigationRoute(CHAT_DETAIL_ROUTE)
        object ReceivedSignalDetailRoute : KeyLinkNavigationRoute(RECEIVED_SIGNAL_DETAIL_ROUTE)
        object ReportRoute : KeyLinkNavigationRoute(REPORT_ROUTE)
        object ChatReplyRoute : KeyLinkNavigationRoute(CHAT_REPLY_ROUTE)
        object ReceivedSignalReplyRoute : KeyLinkNavigationRoute(RECEIVED_SIGNAL_REPLY_ROUTE)
    }
}
