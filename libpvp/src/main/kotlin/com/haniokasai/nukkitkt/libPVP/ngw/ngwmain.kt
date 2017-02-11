package com.haniokasai.nukkitkt.libPVP.ngw

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.Config
import cn.nukkit.utils.TextFormat
import com.haniokasai.nukkitkt.libPVP.mysql.mysql
import java.sql.Date
import java.util.HashMap



/**
 * Created by hani on 2017/02/11.
 */

class ngwmain : PluginBase() ,Listener {
    var gunlangjpn: Config? = null
    var cfg: Config? = null
    var skillc: Config? = null
    var canchat: Map<String, Boolean> = HashMap()
    var chatred = TextFormat.RED
    var chatblue = TextFormat.BLUE
    var chataqua = TextFormat.AQUA
    var chatlpurple = TextFormat.LIGHT_PURPLE
    var gametype = 0
    var servertime = 60
    var servertime1 = 15

    init {

    }

    @EventHandler
    fun joEvent(e: PlayerJoinEvent) {
        mysql.cre8(e.getPlayer().getName())
        val date = Date((mysql.get(e.getPlayer().getName(), "llogin") as Long * 1000))
        e.getPlayer().sendMessage(chatred + "" + "Last Login:" + date)
        teamsys.ctime = (System.currentTimeMillis() / 1000).toInt()
        mysql.set(e.getPlayer().getName(), "llogin", teamsys.ctime)
        if (mysql.get(e.getPlayer().getName(), "isvip") === 3)
        {
            e.getPlayer().setOp(true)
        }
        setn(e.getPlayer())
        if (mysql.canchat(e.getPlayer().getName()))
        {
            canchat.put(e.getPlayer().getName(), true)
        }
        else
        {
            canchat.put(e.getPlayer().getName(), false)
        }
    }
    fun onCommand(sender:CommandSender, command:Command, label:String, args:Array<String>):Boolean {
        val name = sender.getName()
        when (command.getName()) {
            "mi" -> {
                val pmap = mysql.getskills(name)
                sender.sendMessage(chatred + "" + "/*/*/*/*/PlayerData/*/*/*/*/")
                sender.sendMessage(chatred + "" + "Money : " + pmap.get("money"))
                val mai = pmap.get("gslot")
                val i = 1
                while (mai >= i)
                {
                    sender.sendMessage(chatred + "Gun" + i + " : " + "GunName:" + gunlangjpn.getString(pmap.get("gun" + i) + "_name") + "　Gun Num.:" + pmap.get("gun" + i) + " Price:" + gunlangjpn.getString(pmap.get("gun" + i) + "_hm"))
                    i++
                }
                if (Main.skillc.exists("s" + pmap.get("skill") + "name"))
                {
                    sender.sendMessage(chatred + "" + "Skill : " + skillc.getString("s" + pmap.get("skill") + "name"))
                }
                sender.sendMessage(chatred + "" + "/*/*/*/*/*/*/*/*/*/*/*/*/*//")
            }//ChatInWorld a = new ChatInWorld();
        //a.sendchat("aaa", "aaaa",Server.getInstance().getDefaultLevel().getName());
            "stat" -> if (gametype === 0)
            {
                sender.sendMessage(chatred + "" + "" + "/*/*/*/*/TEAM DATA/*/*/*/*/")
                sender.sendMessage(chatred + "" + "Team S HP: " + teamsys.teamhp1 + " Players: " + teamsys.p1team.size())
                sender.sendMessage(chatred + "" + "Team M HP: " + teamsys.teamhp2 + " Players: " + teamsys.p2team.size())
                sender.sendMessage(chatred + "" + "/*/*/*/*/*/*/*/*/*/*/*/*/*/")
            }
            "rekit" -> gunsys.rekit(sender as Player)
            "cg" -> if (args.size < 1)
            {
                sender.sendMessage(chatred + "" + "[ArrowGun] " + lj.cmd_cg_help)
            }
            else
            {
                if (gunlangjpn.exists(args[0] + "_hm"))
                {
                    sender.sendMessage(chatred + "" + "[ArrowGun] GunName:" + gunlangjpn.getString(args[0] + "_name") + "　GunNum:" + args[0] + " GunPrice:" + gunlangjpn.getString(args[0] + "_hm"))
                }
                else
                {
                    sender.sendMessage(chatred + "" + "[ArrowGun] " + lj.cmd_cg_nogun)
                }
            }
        ///////チェック
            "ch" -> if (args.size != 1 || canchat.get(name))
            {
                sender.sendMessage(chatred + "" + "[ArrowGun] " + lj.cantchat)
            }
            else
            {
                if (args[0] == "nuclearcraft")
                {
                    sender.sendMessage(chatblue + "" + "[ArrowGun] " + lj.cmd_ch_true)
                    mysql.set(name, "chat", 2)
                    canchat.put(name, true)
                    mysql.add(name, "money1", 300)
                }
                else
                {
                    sender.sendMessage(chatblue + "" + "[ArrowGun] " + lj.cmd_ch_diff)
                }
            }
            "c" -> if (gametype === 0)
            {
                val msg = ""
                for (arg in args)
                {
                    msg += arg + " "
                }
                if (!teamsys.joinedpvp.containsKey(name))
                {
                    sender.sendMessage(chatred + "" + "[ArrowGun]" + lj.cmd_c_inpvp)
                    break
                }
                if (!canchat.get(name))
                {
                    sender.sendMessage(chatred + "" + "[Arrowgun]" + lj.cantchat)
                    break
                }
                if (msg.length > 0)
                {
                    msg = msg.substring(0, msg.length)
                    if (teamsys.p1team.containsKey(name))
                    {
                        val onlinePlayers = Server.getInstance().getOnlinePlayers()
                        for (online in onlinePlayers.entrySet())
                        {
                            if (teamsys.p1team.containsKey(online.getValue().getName()))
                            {
                                online.getValue().sendMessage(chatblue + "" + "[TEAMCHAT]" + sender.getName() + ":" + msg)
                            }
                        }
                    }
                    else if (teamsys.p2team.containsKey(name))
                    {
                        val onlinePlayers = Server.getInstance().getOnlinePlayers()
                        for (online in onlinePlayers.entrySet())
                        {
                            if (teamsys.p2team.containsKey(online.getValue().getName()))
                            {
                                online.getValue().sendMessage(chatblue + "" + "[TEAMCHAT]" + sender.getName() + ":" + msg)
                            }
                        }
                    }
                }
            }
            "pvp" -> if (teamsys.joinedpvp.containsKey(name))
            {
                sender.sendMessage(chatred + "" + "[ArrowGun] " + lj.cmd_pvp_inpvp)
            }
            else
            {
                val pos1:Vector3
                teamsys.joinedpvp.put(name, 1)
                if (teamsys.teamse(name))
                {
                    if (gametype === 0)
                    {
                        sender.sendMessage(chatlpurple + "" + "[ArrowGun] " + lj.cmd_pvp_ts)
                    }
                    pos1 = Vector3(config.getInt("pos1x"), config.getInt("pos1y"), config.getInt("pos1z"))//座標を指定
                }
                else
                {
                    if (gametype === 0)
                    {
                        sender.sendMessage(chataqua + "" + "[ArrowGun] " + lj.cmd_pvp_tm)
                    }
                    pos1 = Vector3(config.getInt("pos2x"), config.getInt("pos2y"), config.getInt("pos2z"))//座標を指定
                }
                (sender as Player).teleport(pos1)
                teamsys.ctime = (System.currentTimeMillis() / 1000).toInt()
                teamsys.pteamti.put(name, teamsys.ctime)
                (sender as Player).getInventory().clearAll()
                gunsys.rekit(sender as Player)
                val d = 350
                if (gametype === 0)
                {
                    d = 200
                }
                (sender as Player).addEffect(Effect.getEffect(11).setDuration(d).setAmplifier(1000).setVisible(true))
                (sender as Player).getInventory().addItem(Item.get(364, 0, 5).clone())
                (sender as Player).getInventory().addItem(Item.get(257, 0, 1).clone())
                setn(sender as Player)
                (sender as Player).getInventory().sendContents((sender as Player))
            }
            "pban" -> if (mysql.get(name, "isvip") >= 2 || sender.isOp())
            {
                try
                {
                    if (Server.getInstance().getPlayer(args[0]) != null)
                    {
                        val pp = Server.getInstance().getPlayer(args[0])
                        Server.getInstance().getIPBans().addBan(pp.getAddress(), "PBAN:" + sender.getName(), null, args.toString())//ip-ban
                        Server.getInstance().getNameBans().addBan(pp.getName(), "PBAN:" + sender.getName(), null, args.toString())//ba
                        pp.kick(" [BAN] " + pp.getName() + " was banned." + pp.getAddress())
                        Server.getInstance().broadcastMessage(" [BAN] " + pp.getName() + " was banned." + pp.getAddress())
                    }
                    else
                    {
                        sender.sendMessage(chataqua + "" + "[ArrowGun] Who is this !")
                    }
                }
                catch (e:ArrayIndexOutOfBoundsException) {
                    sender.sendMessage(chataqua + "" + "[ArrowGun] Who is this")
                }
            }
        }
        return true
    }
    @EventHandler
    fun noop(event:PlayerCommandPreprocessEvent) {
        val str = event.getMessage().split(" ")
        if (str[0] == "/op")
        {
            val player = event.getPlayer()
            val name = player.getName()
            val reason = " [ArrowGun] " + name + " was banned. because he use /op"
            val ip = player.getAddress()
            Server.getInstance().broadcastMessage(" [OPBAN] " + name + " was banned." + ip)
            Server.getInstance().getIPBans().addBan(ip, "OPBAN", null, reason)//ip-ban
            player.kick(" [OPBAN] " + name + " was banned." + ip)
        }
    }
    fun c_reader(config:String):File {
        val file = File(getDataFolder() + "/" + config)
        return file
    }

}