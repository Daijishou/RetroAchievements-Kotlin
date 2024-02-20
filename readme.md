<img align="left" src="https://i.imgur.com/gUxw8Nd.png" alt="drawing" width="150" style="margin-right: 20px;"/>

# RetroAchievements - Kotlin
This is a straightforward client written in Kotlin, designed to navigate and query the https://RetroAchievements.org/ API.

---

## Getting Started

To begin, import the library using jitpack.io.

You can include jitpack in your pom.xml by using the following code:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://www.jitpack.io</url>
</repository>
```

Then add this Metron Comics Client Library to your project!

```xml
<dependency>
    <groupId>com.github.Daijishou</groupId>
    <artifactId>RetroAchievements-kotlin</artifactId>
    <version>1.0.0</version>
</dependency>
```
---

## Credentials
To gain your credentials to access the API you must create an account on https://RetroAchievements.org/, 
the username used during the registration process will be your username for the API, and the ApiKey can be found within https://retroachievements.org/controlpanel.php under **Keys**

# Usage
To begin, create an instance of the Client/API to access RetroAchievements!

## Create instance
```kotlin
// your RetroAchievements credentials
val credentials: RetroCredentials = RetroCredentials("<username>", "<Web API Key>")

// client instance 
val client: RetroClient = RetroClient(credentials)

// api access
val api = client.api
```
---

### Note
For a list of functions, check the ```RetroInterface``` class!

---
## Search Game Example
A call to this endpoint will retrieve basic metadata about a game, targeted via its unique ID.

### Example
```kotlin
// your RetroAchievements credentials
val credentials: RetroCredentials = RetroCredentials("<username>", "<Web API Key>")

// client instance 
val client: RetroClient = RetroClient(credentials)

// api access
val api: RetroInterface = client.api

val response: NetworkResponse<GetGame.Response, ErrorResponse> = api.getGame(14402)
if (response is NetworkResponse.Success) {
    // do as you wish with the response body, it'll be parsed into the appropriate pojo object
    val body: GetGame.Response = response.body

} else if (response is NetworkResponse.Error) {
    // if the server returns an error it be found here
    val errorResponse: ErrorResponse? = response.body

    // if the api (locally) had an internal error, it'll be found here
    val internalError: Throwable? = response.error
}
```
## Special Thanks
Special thanks to [@official-wizard](https://www.github.com/official-wizard) who generously created this beautiful library for Daijishou.
