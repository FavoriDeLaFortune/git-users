package com.example.git_users.domain.utils

import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.ui.model.ExtendedInfoState
import com.example.git_users.ui.model.Profile

internal fun ProfileNetworkModel.toProfile(): Profile {
    return Profile(
        login = login,
        avatarUrl = avatarUrl,
        extendedInfo = ExtendedInfoState.Success(
            name = name,
            company = company,
            email = email,
            blog = blog,
            location = location,
            reposCount = reposCount,
            followers = followers,
            bio = bio
        )
    )
}