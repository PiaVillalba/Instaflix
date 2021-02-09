package com.piavillalba.core.extensions

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import com.piavillalba.core.constants.DeepLink

fun View.startDeepLinkIntent(deepLink: DeepLink) {
    val uri = Uri.parse(deepLink)
    findNavController().navigate(uri)
}
