package com.mashup.data.source.remote.service

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
object ApiPattern {
    object Keywords {
        private const val PREFIX = "keywords/"
        const val RECOMMEND = PREFIX + "recommend"
    }

    object Signal {
        private const val PREFIX = "signal/"
        const val SEND_SIGNAL = PREFIX + "send"
    }
}