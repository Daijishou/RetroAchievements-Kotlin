package retroachivements.api.data.pojo.user

import com.google.gson.annotations.SerializedName

class GetUserRecentAchievements {

    class Response : ArrayList<Response.Achievement>() {
        data class Achievement(
            @SerializedName("Date")
            val date: String,

            @SerializedName("HardcoreMode")
            val hardcoreMode: Int,

            @SerializedName("AchievementId")
            val achievementId: Long,

            @SerializedName("Title")
            val title: String,

            @SerializedName("Description")
            val description: String,

            @SerializedName("BadgeName")
            val badgeName: String,

            @SerializedName("Points")
            val points: Int,

            @SerializedName("Type")
            val type: String?,

            @SerializedName("Author")
            val author: String,

            @SerializedName("GameTitle")
            val gameTitle: String,

            @SerializedName("GameIcon")
            val gameIcon: String,

            @SerializedName("GameId")
            val gameId: Long,

            @SerializedName("ConsoleName")
            val consoleName: String,

            @SerializedName("BadgeUrl")
            val badgeUrl: String,

            @SerializedName("GameUrl")
            val gameUrl: String
        )
    }
}