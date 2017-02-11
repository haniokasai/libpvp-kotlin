package com.haniokasai.nukkitkt.libPVP.ngw

import cn.nukkit.Player
import cn.nukkit.event.Listener
import cn.nukkit.utils.TextFormat
import com.haniokasai.nukkitkt.libPVP.mysql.mysql
import java.io.UnsupportedEncodingException
import java.util.*

/**
 * Created by hani on 2017/02/11.
 */

class tools : Listener {
    fun setn(player: Player) {
        val name = player.getName()
        val teamname = ""
        val tcolor = TextFormat.RESET
        var r2 = ""
        var op = ""
        if (ngwmain.gametype === 0)
        {
            if (teamsys.p1team.containsKey(name))
            {
                teamname = "[S]"
                tcolor = TextFormat.LIGHT_PURPLE
            }
            else if (teamsys.p2team.containsKey(name))
            {
                teamname = "[M]"
                tcolor = TextFormat.AQUA
            }
        }
        val r = mysql.getname(name)
        if (r!!.length > 2)
        {
            try
            {
                r2 = "[" + Base64.getDecoder().decode(r)+ "]"
            }
            catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        if (player.isOp())
        {
            op = "[OP]"
        }
        else if (mysql.get(name, "isvip") === 1)
        {
            op = "[VIP]"
        }
        val q = if (mysql.getregion(name) === 1) "" else "[ENG]"
        val kk = mysql.get(name, "ap1")
        player.setDisplayName(tcolor + "" + op + q + teamname + r2 + name + "[" + kk + "AP]")
        player.setNameTag(tcolor + "" + op + q + teamname + r2 + name + "[" + kk + "AP]")
    }


}