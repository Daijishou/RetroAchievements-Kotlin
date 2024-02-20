package retroachivements.api

import retroachivements.api.core.CoreClient
import retroachivements.api.data.RetroCredentials

class RetroClient(credentials: RetroCredentials, debugging: Boolean = false)
    : CoreClient(credentials, "https://retroachievements.org/", debugging) {

    // the API interface for the client
    val api: RetroInterface = retroClient.create(RetroInterface::class.java)
}