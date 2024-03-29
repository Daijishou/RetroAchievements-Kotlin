package retroachivements.api.data.pojo.system

import com.google.gson.annotations.SerializedName

class GetConsoleID {

    class Response: ArrayList<Response.Console>() {
        data class Console (
            @SerializedName("ID")
            val id: String,

            @SerializedName("Name")
            val name: String
        )
    }
}