package com.mashup.presentation.navigation

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
sealed class KeyLinkNavigationRoute(val route: String) {
    object HomeGraph : KeyLinkNavigationRoute(HOME_GRAPH_PATTERN) {
        object HomeRoute : KeyLinkNavigationRoute(HOME_ROUTE)
        object SubscribeKeywordRoute : KeyLinkNavigationRoute(SUBSCRIBE_KEYWORD_PATTERN)
        object GuideRoute : KeyLinkNavigationRoute(GUIDE_ROUTE)
        object ProfileRoute : KeyLinkNavigationRoute(PROFILE_ROUTE)
    }
    object SignalGraph : KeyLinkNavigationRoute(SIGNAL_GRAPH_PATTERN) {
        object SignalContentRoute : KeyLinkNavigationRoute(SIGNAL_CONTENT_ROUTE)
        object SignalKeywordRoute : KeyLinkNavigationRoute(SIGNAL_KEYWORD_ROUTE)
        object SignalCompleteRoute : KeyLinkNavigationRoute(SIGNAL_COMPLETE_ROUTE)
    }

    object ChatGraph : KeyLinkNavigationRoute(CHAT_GRAPH_PATTERN) {
        object ChatRoute : KeyLinkNavigationRoute(CHAT_ROUTE)
        object ChatDetailRoute : KeyLinkNavigationRoute(CHAT_DETAIL_ROUTE)
        object MessageDetailRoute : KeyLinkNavigationRoute(MESSAGE_DETAIL_ROUTE)
        object ReportRoute : KeyLinkNavigationRoute(REPORT_ROUTE)
    }

    object ProfileGraph : KeyLinkNavigationRoute(PROFILE_GRAPH_PATTERN) {
        object ProfileRoute : KeyLinkNavigationRoute(PROFILE_ROUTE)
        object SendSignalRoute : KeyLinkNavigationRoute(SEND_SIGNAL_ROUTE)
        object TermsOfServiceRoute : KeyLinkNavigationRoute(TERMS_OF_SERVICE_ROUTE)
        object PrivacyPolicyRoute : KeyLinkNavigationRoute(PRIVACY_POLICY_ROUTE)
        object OpenSourceRoute : KeyLinkNavigationRoute(OPEN_SOURCE_ROUTE)
    }
}
