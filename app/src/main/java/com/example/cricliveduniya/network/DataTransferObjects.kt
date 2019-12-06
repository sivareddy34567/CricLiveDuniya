package com.example.cricliveduniya.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllMatches(
        val url: MatchUrl,
        val matches: List<Matches>,
        val srs_category : List<SourceCategory> )


@JsonClass(generateAdapter = true)
data class MatchUrl(val match : String)

@JsonClass(generateAdapter = true)
data class Matches(
        val match_id : Int,
        val series_id : Int,
        val series_name : String,
        val data_path : String,
        val header : HeaderMatch,
        val alerts : Int,
        val venue : Venue,
        val bat_team : BatRBowlTeams = BatRBowlTeams(),
        val bow_team : BatRBowlTeams = BatRBowlTeams(),
        val batsman : List<Batsman> = ArrayList(),
        val bowler : List<Bowler> = ArrayList(),
        val team1 : Team,
        val team2 : Team,
        val srs_category : List<Int>){
    val title : String get() = header.match_desc+ ", "+ series_name
}

@JsonClass(generateAdapter = true)
data class SourceCategory(
        val id : Int,
        val title : String)

@JsonClass(generateAdapter = true)
data class HeaderMatch(
        val start_time : String,
        val end_time : String,
        val state : String,
        val dn : Int = 0,
        val winning_team_id : Int = 0,
        val match_desc : String,
        val mom : List<Int> = ArrayList(),
        val momNames : List<String> = ArrayList(),
        val mos : List<Int> = ArrayList(),
        val mosNames : List<String> = ArrayList(),
        val type : String,
        val state_title : String,
        val toss : String,
        val status : String)

@JsonClass(generateAdapter = true)
data class Venue(
        val name : String,
        val location : String,
        val timezone : String,
        val lat : String,
        val long : String)

@JsonClass(generateAdapter = true)
class BatRBowlTeams(
        val id : Int = 0,
        val innings : List<Innings> = ArrayList())

@JsonClass(generateAdapter = true)
data class Innings(
        val id : String,
        val score: String,
        val decl : String = "",
        val follow_on : String = "",
        val wkts : String,
        val overs : String)

@JsonClass(generateAdapter = true)
data class Batsman(
        val id : String,
        val name : String,
        val strike : String,
        val r : String,
        val b : String,
        @Json(name = "4s")val fours : String = "",
        @Json(name = "6s")val sixs : String = ""
)


@JsonClass(generateAdapter = true)
data class Bowler(
        val id : String,
        val name : String,
        val o : String,
        val m : String,
        val r : String,
        val w : String)

@JsonClass(generateAdapter = true)
data class Team(
        val id : String,
        val name : String,
        val s_name : String,
        val flag : String)




