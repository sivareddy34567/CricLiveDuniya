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

    fun inningsTotal(id : Int) : String{
        if (Innings.size>id) {
            return "${Innings.get(id).score}(${Innings.get(id).wkts}wkts,${Innings.get(id).ovr}Ov)"
        }
        else return ""
    }

    fun inningsExtras(id : Int) : String{
        if (Innings.size>id) {
            val ext : InningsExtras = Innings.get(id).extras
            return ext.t+"(b ${ext.b},lb ${ext.lb},wd ${ext.wd},nb ${ext.nb},p ${ext.p})"
        }
        else return ""
    }

    fun inningsscore(id: Int) : String {
        if (Innings.size>id) {
            return Innings.get(id).score+"-"+Innings.get(id).wkts+"(${Innings.get(id).ovr})"
        }
        else return ""
    }

    fun sInnings(id:Int) : String {
        if (Innings.size>id) {
            if (LiveScoreViewModel.type.equals("TEST")){
                if (Innings.get(id).innings_id.toInt() == 3 || Innings.get(id).innings_id.toInt() == 4) {
                    return Innings.get(id).bat_team_name+ " 2nd Innings"
                }
                else return Innings.get(id).bat_team_name+ " 1st Innings"
            }
            else return Innings.get(id).bat_team_name+ "  Innings"

        }
        else return ""
    }
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
