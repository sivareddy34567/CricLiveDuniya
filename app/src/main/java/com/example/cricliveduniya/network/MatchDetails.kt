package com.example.cricliveduniya.network

import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.RawValue

@JsonClass(generateAdapter = true)
data class MatchDetails(
    val url: MatchUrl,
    val match_id : String,
    val series_id : String,
    val series_name : String,
    val data_path : String,
    val header : @RawValue HeaderMatch,
    val alerts : String,
    val venue : @RawValue Venue,
    val official : @RawValue Official,
    val toss : @RawValue Toss,
    val team1 : @RawValue Teams,
    val team2 : @RawValue Teams,
    val players : @RawValue List<Players>

) {
}

@JsonClass(generateAdapter = true)
data class Official(
    val umpire1 : @RawValue Umpire,
    val umpire2 : @RawValue Umpire,
    val referee : @RawValue Umpire) {

}

@JsonClass(generateAdapter = true)
data class Umpire(
    val id : String,
    val name : String,
    val country : String) {

}

@JsonClass(generateAdapter = true)
data class Toss(
    val winner : String,
    val decision : String) {

}

@JsonClass(generateAdapter = true)
data class Teams(
    val id : String,
    val name : String,
    val s_name : String,
    val flag : String,
    val squad : @RawValue List<Int>,
    val squad_bench : @RawValue List<Int>) {

}

@JsonClass(generateAdapter = true)
data class Players(
    val id : String,
    val f_name : String,
    val name : String,
    val bat_style : String,
    val bowl_style : String = "",
    val speciality : String,
    val role : String = "",
    val image : String) {

}
