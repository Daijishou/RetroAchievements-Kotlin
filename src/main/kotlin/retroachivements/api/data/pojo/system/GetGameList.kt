package retroachivements.api.data.pojo.system

import com.google.gson.annotations.SerializedName

class GetGameList {

    class Response: ArrayList<Response.Game>() {
        data class Game(
            @SerializedName("ID")
            val id: Long,

            @SerializedName("Title")
            val title: String,

            @SerializedName("ConsoleID")
            val consoleId: Long,

            @SerializedName("ConsoleName")
            val consoleName: String,

            @SerializedName("ImageIcon")
            val imageIcon: String,

            @SerializedName("NumAchievements")
            val numAchievements: Long,

            @SerializedName("NumLeaderboards")
            val numLeaderboards: Long,

            @SerializedName("Points")
            val points: Long,

            @SerializedName("DateModified")
            val dateModified: String,

            @SerializedName("ForumTopicId")
            val forumTopicId: Long,

            @SerializedName("Hashes")
            val hashes: ArrayList<String>
        )
    }
}