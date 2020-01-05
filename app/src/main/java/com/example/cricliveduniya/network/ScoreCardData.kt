package com.example.cricliveduniya.network

import androidx.core.text.isDigitsOnly
import com.example.cricliveduniya.ui.livescore.LiveScoreViewModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.RawValue

@JsonClass(generateAdapter = true)
data class ScoreCard(
    val Innings : @RawValue List<InningsItem> = ArrayList(),
    val status : String,
    val state : String){

    val fInnings : String get() = (when {
        Innings.size>1-> Innings.get(1).bat_team_name
        Innings.size == 1 -> Innings.get(0).bat_team_name
        else -> ""
    }).toString()

    val sInnings : String get() = Innings.get(0).bat_team_name
}

@JsonClass(generateAdapter = true)
data class InningsItem(
    val innings_id : String,
    val bat_team_id : String,
    val bat_team_name : String = "",
    val bowl_team_id : String,
    val score : String,
    val wkts : String,
    val ovr : String,
    val batsmen : @RawValue List<BatsmanId> = ArrayList(),
    val next_batsman : String = "",
    val next_batsman_label : String = "",
    val bowlers : @RawValue List<BowlerId> = ArrayList(),
    val extras : @RawValue InningsExtras,
    val fow : @RawValue List<FOW> = ArrayList(),
    val pplay : @RawValue List<PPLAY> = ArrayList()) {

}

@JsonClass(generateAdapter = true)
data class PPLAY(
    val type : String,
    val from : String,
    val to : String,
    val runs : String,
    val Wickets : String) {

}

@JsonClass(generateAdapter = true)
data class FOW(
    val id : String,
    val score : String,
    val wkt_nbr : String,
    val over : String) {

}

@JsonClass(generateAdapter = true)
data class InningsExtras(
    val t : String,
    val b : String,
    val lb : String,
    val wd : String,
    val nb : String,
    val p : String) {

}

@JsonClass(generateAdapter = true)
data class BowlerId(
    val id : String,
    val o : String,
    val m : String,
    val r : String,
    val w : String,
    val n : String,
    val wd : String) {
    val nameString : String get() = (
            if (id.isDigitsOnly()){
                if (LiveScoreViewModel.playersnames.size>0){
                    LiveScoreViewModel.playersnames.get(id)!!.name
                }
                else{
                    id
                }
            }
            else{
                id
            }
            ).toString()
}

@JsonClass(generateAdapter = true)
data class BatsmanId(
    val id : String,
    val out_desc : String,
    val r : String,
    val b : String,
    @Json(name = "4s")val fours : String = "",
    @Json(name = "6s")val sixs : String = "") {

    val nameString : String get() = (
            if (id.isDigitsOnly()){
                if (LiveScoreViewModel.playersnames.size>0){
                    LiveScoreViewModel.playersnames.get(id)!!.name
                }
                else{
                    id
                }
            }
            else{
                id
            }
            ).toString()


}
