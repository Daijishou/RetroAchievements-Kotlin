package retroachivements.api

import com.haroldadmin.cnradapter.NetworkResponse
import retroachivements.api.data.pojo.user.*
import retroachivements.api.core.DateFormat
import retroachivements.api.core.RequiresCache
import retroachivements.api.data.pojo.ErrorResponse
import retroachivements.api.data.pojo.achievement.GetAchievementUnlocks
import retroachivements.api.data.pojo.event.GetAchievementOfTheWeek
import retroachivements.api.data.pojo.feed.GetClaims
import retroachivements.api.data.pojo.feed.GetTopTenUsers
import retroachivements.api.data.pojo.game.*
import retroachivements.api.data.pojo.system.GetConsoleID
import retroachivements.api.data.pojo.system.GetGameList
import retroachivements.api.data.pojo.ticket.*
import retroachivements.api.data.pojo.user.GetUserCompletedGames
import retroachivements.api.data.pojo.user.GetUserRecentlyPlayedGames
import retroachivements.api.data.pojo.user.GetUserSummary
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface RetroInterface {

    /**
     * A call to this function will retrieve the current top ten users
     * on the site.
     */
    @GET("/API/API_GetTopTenUsers.php")
    suspend fun getTopTenUsers(): NetworkResponse<GetTopTenUsers.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve minimal user profile information, such as their ID, motto, most recent game ID, avatar, and points.
     */
    @POST("/API/API_GetUserProfile.php")
    suspend fun getUserProfile(@Query("u") username: String): NetworkResponse<GetUserProfile.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve a list of a target user's recently unlocked achievements, via their username. By default, it fetches achievements unlocked in the last hour.
     */
    @POST("/API/API_GetUserRecentAchievements.php")
    suspend fun getUserRecentAchievements(
        @Query("u") username: String,
        @Query("m") minutes: Long = 60
    ): NetworkResponse<GetUserRecentAchievements.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve a list of achievements unlocked by a given user between two given dates.
     */
    @POST("/API/API_GetAchievementsEarnedBetween.php")
    suspend fun getAchievementsEarnedBetween(
        @Query("u") username: String,
        @Query("f") fromTimestamp: Long,
        @Query("t") toTimestamp: Long
    ): NetworkResponse<GetUserRecentAchievements.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve a list of achievements unlocked by a given user on a specified date.
     */
    @POST("/API/API_GetAchievementsEarnedOnDay.php")
    suspend fun getAchievementsEarnedOnDay(
        @Query("u") username: String,
        @Query("d") @DateFormat("yyyy-MM-dd") date: Date
    ): NetworkResponse<GetUserRecentAchievements.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve a list of achievements unlocked by a given user between two given dates.
     */
    @POST("/API/API_GetGameInfoAndUserProgress.php")
    suspend fun getGameInfoAndUserProgress(
        @Query("u") username: String,
        @Query("g") gameId: Long
    ): NetworkResponse<GetGameInfoAndUserProgress.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve a giver user's completion progress, targeted by their username.
     */
    @POST("/API/API_GetUserCompletionProgress.php")
    suspend fun getUserCompletionProgress(
        @Query("u") username: String,
        @Query("c") maxRecords: Int = 100,
        @Query("o") skipRecords: Int = 0
    ): NetworkResponse<GetUserCompletionProgress.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve metadata about the target user's site awards, via their username.
     */
    @POST("/API/API_GetUserAwards.php")
    suspend fun getUserAwards(
        @Query("u") username: String
    ): NetworkResponse<GetUserAwards.Response, ErrorResponse>

    /**
     * A call to this function will retrieve a list of achievement set claims made over the lifetime of a given user, targeted by their username.
     */
    @POST("/API/API_GetUserClaims.php")
    suspend fun getUserClaims(
        @Query("u") username: String
    ): NetworkResponse<GetUserClaims.Response, ErrorResponse>

    /**
     * A call to this function will retrieve metadata about how a given user has performed/ranked on a given game, targeted by game ID.
     */
    @POST("/API/API_GetUserGameRankAndScore.php")
    suspend fun getUserGameRankAndScore(
        @Query("u") username: String,
        @Query("g") gameId: Long
    ): NetworkResponse<GetUserGameRankAndScore.Response, ErrorResponse>

    /**
     * A call to this function will retrieve a given user's hardcore and softcore points.
     */
    @POST("/API/API_GetUserPoints.php")
    suspend fun getUserPoints(
        @Query("u") username: String,
    ): NetworkResponse<GetUserPoints.Response, ErrorResponse>

    /**
     * A call to this function will retrieve a given user's progress on a given list of games, targeted by game ID.
     */
    @POST("/API/API_GetUserProgress.php")
    suspend fun getUserProgress(
        @Query("u") username: String,
        @Query("i") gameId: String
    ): NetworkResponse<GetUserProgress.Response, ErrorResponse>

    /**
     * A call to this function will retrieve a list of a target user's recently played games, via their username.
     */
    @POST("/API/API_GetUserRecentlyPlayedGames.php")
    suspend fun getUserRecentlyPlayedGames(
        @Query("u") username: String,
        @Query("c") count: Int = 10,
        @Query("o") offset: Int = 0
    ): NetworkResponse<GetUserRecentlyPlayedGames.Response, ErrorResponse>

    /**
     * A call to this function will retrieve summary information about a given user, targeted by username.
     */
    @POST("/API/API_GetUserSummary.php")
    suspend fun getUserSummary(
        @Query("u") username: String
    ): NetworkResponse<GetUserSummary.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve completion metadata about the games a given user has played.
     * It returns two entries per each game: one for the softcore completion and one for the hardcore completion.
     * These are designated by the hardcoreMode property on each completion object.
     */
    @POST("/API/API_GetUserCompletedGames.php")
    @Deprecated("This endpoint is considered legacy. The `getUserCompletionProgress` endpoint will almost always be a better fit for your use case.")
    suspend fun getUserCompletedGames(
        @Query("u") username: String
    ): NetworkResponse<GetUserCompletedGames.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve basic metadata about a game, targeted via its unique ID.
     */
    @POST("/API/API_GetGame.php")
    suspend fun getGame(
        @Query("i") gameId: Long
    ): NetworkResponse<GetGame.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve extended metadata about a game, targeted via its unique ID.
     */
    @POST("/API/API_GetGameExtended.php")
    suspend fun getGameExtended(
        @Query("i") gameId: Long
    ): NetworkResponse<GetGameExtended.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve the list of achievement IDs for a game, targeted by game ID.
     * This can be useful if you'd like to quickly check how many achievements a particular game has.
     * Using this, you can also detect if a game has received a revision.
     * For example, if a game had 100 achievements last month and has 102 today,
     * you know the game's achievement set has been revised.
     */
    @POST("/API/API_GetAchievementCount.php")
    suspend fun getAchievementCount(
        @Query("i") gameId: Long
    ): NetworkResponse<GetAchievementCount.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve a dictionary of the number of players who have earned
     * a specific number of achievements for a given game ID. This endpoint can be used to determine
     * the total mastery count for a game, as well as how rare that overall mastery is.
     */
    @POST("/API/API_GetAchievementDistribution.php")
    suspend fun getAchievementDistribution(
        @Query("i") gameId: Long,
        @Query("h") hardcore: Int = 0,
        @Query("f") official: Int = 3
    ): NetworkResponse<GetAchievementDistribution.Response, ErrorResponse>

    /**
     * A call to this function will retrieve metadata about either the latest masters for a game, or the highest points earners for a game. The game is targeted via its unique ID.
     */
    @POST("/API/API_GetGameRankAndScore.php")
    suspend fun getGameRankAndScore(
        @Query("g") gameId: Long,
        @Query("t") masters: Int = 0
    ): NetworkResponse<GetGameRankAndScore.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve the complete list of all system ID and name pairs on the site.
     */
    @POST("/API/API_GetConsoleIDs.php")
    suspend fun getConsoleIds(): NetworkResponse<GetConsoleID.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve the complete list of games for a specified console on the site,
     * targeted by the console ID. If you do not know the console ID you're looking for,
     * try using the all systems endpoint.
     */
    @POST("/API/API_GetGameList.php")
    @RequiresCache
    suspend fun getGameList(
        @Query("i") consoleId: Long,
        @Query("f") shouldOnlyRetrieveGamesWithAchievements: Int = 0,
        @Query("h") shouldRetrieveGameHashes: Int = 0
    ): NetworkResponse<GetGameList.Response, ErrorResponse>

    /**
     * A call to this function will retrieve a list of users who have earned an achievement, targeted by the achievement's ID.
     */
    @POST("/API/API_GetAchievementUnlocks.php")
    @RequiresCache
    suspend fun getAchievementUnlocks(
        @Query("a") achievementId: Long,
        @Query("o") offset: Int = 0,
        @Query("c") count: Int = 50
    ): NetworkResponse<GetAchievementUnlocks.Response, ErrorResponse>

    /**
     * A call to this function returns information about all (1000 max)
     * set claims of a specified kind: completed=1, dropped=2, or expired=3.
     */
    @POST("/API/API_GetClaims.php")
    @RequiresCache
    suspend fun getClaims(
        @Query("k") claimKind: Int
    ): NetworkResponse<GetClaims.Response, ErrorResponse>

    /**
     * A call to this function returns information about all (1000 max) active set claims.
     */
    @POST("/API/API_GetActiveClaims.php")
    @RequiresCache
    suspend fun getActiveClaims(): NetworkResponse<GetClaims.Response, ErrorResponse>

    /**
     * A call to this endpoint will retrieve comprehensive metadata about the current Achievement of the Week.
     */
    @POST("/API/API_GetAchievementOfTheWeek.php")
    @RequiresCache
    suspend fun getAchievementOfTheWeek(): NetworkResponse<GetAchievementOfTheWeek.Response, ErrorResponse>

    /**
     * A call to getTicketData() in this manner will retrieve ticket metadata information about
     * a single achievement ticket, targeted by its ticket ID.
     */
    @POST("/API/API_GetTicketData.php")
    @RequiresCache
    suspend fun getTicketDataByTicketId(
        @Query("i") ticketId: Long
    ): NetworkResponse<GetTicketData.Response, ErrorResponse>

    /**
     * A call to getTicketData() in this manner will retrieve the games on the site with the highest count of opened achievement tickets.
     */
    @POST("/API/API_GetTicketData.php")
    @RequiresCache
    suspend fun getMostTicketedGames(
        @Query("c") count: Int = 10,
        @Query("o") offset: Int = 0,
        @Query("f") type: Int = 1
    ): NetworkResponse<GetMostTicketedGames.Response, ErrorResponse>

    /**
     * A call to getTicketData() in this manner will retrieve the most recent tickets
     */
    @POST("/API/API_GetTicketData.php")
    @RequiresCache
    suspend fun getMostRecentTickets(
        @Query("c") count: Int = 10,
        @Query("o") offset: Int = 0
    ): NetworkResponse<GetMostRecentTickets.Response, ErrorResponse>

    /**
     * A call to getTicketData() in this manner will retrieve the most recent tickets
     * to set [shouldReturnTicketsList], set the value to `1`
     * to set [isGettingTicketsForUnofficialAchievements], set the value to `5`
     */
    @POST("/API/API_GetTicketData.php")
    @RequiresCache
    suspend fun getGameTicketStats(
        @Query("g") gameId: Long,
        @Query("d") shouldReturnTicketsList: Int? = null,
        @Query("f") isGettingTicketsForUnofficialAchievements: Int? = null,
        @Query("c") count: Int = 10,
        @Query("o") offset: Int = 0
    ): NetworkResponse<GetGameTicketStats.Response, ErrorResponse>

    /**
     * A call to getTicketData() in this manner will retrieve ticket stats for a developer, targeted by that developer's site username.
     */
    @POST("/API/API_GetTicketData.php")
    @RequiresCache
    suspend fun getDeveloperTicketStats(
        @Query("u") username: String
    ): NetworkResponse<GetDeveloperTicketStats.Response, ErrorResponse>

    /**
     * A call to getTicketData() in this manner will retrieve ticket stats for an achievement, targeted by that achievement's unique ID.
     */
    @POST("/API/API_GetTicketData.php")
    @RequiresCache
    suspend fun getAchievementTicketStats(
        @Query("a") achievementId: Long
    ): NetworkResponse<GetAchievementTicketStats.Response, ErrorResponse>


}