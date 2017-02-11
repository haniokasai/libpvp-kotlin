package com.haniokasai.nukkitkt.libPVP.lang

/**
 * Created by hani on 2017/02/11.
 */

object lj {

    internal var conf_lang = ""
    //teamsys.java
    internal var damage_nojoinpvp = ""
    internal var damage_attackbyhand = ""
    internal var damage_attacksameteam = ""
    internal var core_brakemy = ""
    internal var core_attacked1 = ""
    internal var core_attacked2 = ""
    internal var core_win = ""
    internal var core_serverrestart = ""
    internal var brkhimcore = ""
    internal var damage_killfall_1 = ""
    ///////////////////

    ///gunsys.java
    internal var usebu_remaining = ""
    internal var usebu_reloadcomp = ""
    internal var usebu_relstart1 = ""
    internal var usebu_relstart2 = ""
    internal var fishingrod_reloading = ""
    internal var rekit_comp = ""
    //////////////////

    //Main.java
    internal var cmd_cg_help = ""
    internal var cmd_cg_nogun = ""
    internal var cmd_ch_diff = ""
    internal var cmd_ch_true = ""
    internal var cmd_c_inpvp = ""
    internal var cmd_pvp_inpvp = ""
    internal var cmd_pvp_ts = ""
    internal var cmd_pvp_tm = ""
    internal var conf_region = ""
    internal var cantchat = ""


    /////


    fun sellang(ln: String) {
        if (ln == "eng") {
            conf_lang = "eng"
            conf_region = "2"
            //teamsys.java
            damage_nojoinpvp = "You can't attack the player not-in PVP."
            damage_attackbyhand = "Do not beat the player"
            damage_attacksameteam = "You can't attack the same team player "
            core_brakemy = "attacked Our Team Core."
            core_attacked1 = " attacked "
            core_attacked2 = " Team's Core."
            core_win = "Team Won. Teleport to the respawn point within 10 seconds"
            core_serverrestart = "Team Won. Server restarts within 20 seconds."
            brkhimcore = "Count down of your Banning"
            damage_killfall_1 = " pushed and killed : "
            ///////////////////

            ///gunsys.java
            usebu_remaining = "Remaining: "
            usebu_reloadcomp = "Reload has been completed"
            usebu_relstart1 = "Started reloading."
            usebu_relstart2 = " seconds"
            fishingrod_reloading = "Reload in..."
            rekit_comp = "Refit is completed."
            //////////////////

            //Main.java
            cmd_cg_help = "/cg  <Gun Num>"
            cmd_cg_nogun = "There are no gun such a number."
            cmd_c_inpvp = "You must in PVP"
            cmd_pvp_inpvp = "You are already join in PVP"
            cmd_pvp_ts = "You are in S TEAM"
            cmd_pvp_tm = "You are in M TEAM"
            cantchat = "You should check secret key from http://ngw.haniokasai.com,and enter /ch <secret key>"
            cmd_ch_diff = "Please enter true secret-key"
            cmd_ch_true = "OK!"

            ////

        } else if (ln === "kor") {

        } else if (ln == "jpn") {
            conf_lang = "jpn"
            conf_region = "1"
            //teamsys.java
            damage_nojoinpvp = "PVPに参加していないプレイヤーは攻撃できません。"
            damage_attackbyhand = "相手を叩いてはいけません"
            damage_attacksameteam = "同チームのプレイヤーは攻撃できません。"
            core_brakemy = "が自チームのコアを攻撃。"
            core_attacked1 = "が"
            core_attacked2 = "チームのコアを攻撃。"
            core_win = "チームが勝ちました。10秒以内にリスポーン地点に移動します"
            core_serverrestart = "チームが勝ちました。サーバーが20秒以内に再起動します。"
            brkhimcore = "自チームのコア破壊によるbanのカウントダウン"
            damage_killfall_1 = " が突き落として倒した: "
            ///////////////////

            ///gunsys.java
            usebu_remaining = "残り弾数: "
            usebu_reloadcomp = "リロード完了しました"
            usebu_relstart1 = "リロード開始しました."
            usebu_relstart2 = "秒かかります"
            fishingrod_reloading = "リロード中です..."
            rekit_comp = "再装備完了。"
            //////////////////

            //Main.java
            cmd_cg_help = "/cg  <銃番号>"
            cmd_cg_nogun = "そのようなidの銃はありません"
            cmd_c_inpvp = " PVPに参加していないと操作できません"
            cmd_pvp_inpvp = "あなたはすでにPVPに参加しています"
            cmd_pvp_ts = "あなたはSチームです"
            cmd_pvp_tm = "あなたはMチームです"
            cantchat = "http://ngw.haniokasai.comから、秘密のパスワードを得て、/ch　秘密のパスワードと入力してください"
            cmd_ch_diff = "秘密のパスワードが違うようです"
            cmd_ch_true = "登録できました"

        }

    }


}