package com.mashup.presentation.navigation

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
sealed class KeyLinkNavigationRoute(val route: String) {
    object HomeGraph : KeyLinkNavigationRoute(homeGraphPattern) {
        object HomeRoute : KeyLinkNavigationRoute(homeRoute)
        object EditKeywordRoute : KeyLinkNavigationRoute(editKeywordRoute)
        object GuideRoute : KeyLinkNavigationRoute(guideRoute)
        object ProfileRoute : KeyLinkNavigationRoute(profileRoute)
    }
    object SignalGraph : KeyLinkNavigationRoute(signalGraphPattern) {
        object SignalContentRoute : KeyLinkNavigationRoute(signalContentRoute)
        object SignalKeywordRoute : KeyLinkNavigationRoute(signalKeywordRoute)
        object SignalCompleteRoute : KeyLinkNavigationRoute(signalCompleteRoute)
    }

    object ChatGraph : KeyLinkNavigationRoute(chatGraphPattern) {
        object ChatRoute : KeyLinkNavigationRoute(chatRoute)
        object ChatDetailRoute : KeyLinkNavigationRoute(chatDetailRoute)
        object MessageDetailRoute : KeyLinkNavigationRoute(messageDetailRoute)
        object ReportRoute : KeyLinkNavigationRoute(reportRoute)
    }
}
