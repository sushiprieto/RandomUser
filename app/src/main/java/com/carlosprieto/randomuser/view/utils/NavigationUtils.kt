package com.carlosprieto.randomuser.view.utils

import com.carlosprieto.randomuser.data.dto.User
import java.net.URLEncoder

object NavigationUtils {

    fun createUserDetailsRoute(user: User): String {
        return buildString {
            append("DetailsView/")
            append(URLEncoder.encode(user.name.first, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.name.last, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.email, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.gender, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.registered.date.substring(0, 10), "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.phone, "UTF-8"))
            append("/")
            append(URLEncoder.encode(user.picture.thumbnail, "UTF-8"))
        }
    }
}