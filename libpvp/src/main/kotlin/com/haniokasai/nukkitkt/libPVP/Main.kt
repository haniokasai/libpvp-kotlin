package com.haniokasai.nukkitkt.libPVP

/**
 * Created by hani on 2017/02/11.
 */

import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.event.Listener
import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.TextFormat
import cn.nukkit.Player
import cn.nukkit.event.EventHandler
import cn.nukkit.event.player.PlayerInteractEvent

/**
 * Created by hani on 2017/01/29.
 */
class Main : PluginBase() , Listener {

    override fun onEnable() {
        // Plugin startup logic
        logger.info("Hello Kotlin!")
        server.getPluginManager().registerEvents(this, this);//必須
    }

    //プレイヤーが触ったブロックのIDがわかります。
    @EventHandler
    fun onTouch(event: PlayerInteractEvent) {//ブロックが触られたら
        val player = event.player///プレイヤー
        val item = event.block//触られたブロック
        player.sendMessage(TextFormat.YELLOW.toString() + "It id is " + item.id)//idをプレイヤーに表示します
    }

    override open fun onCommand(sender : CommandSender, command: Command, label : String, vararg args : String): Boolean {
        when (command.name) {
            "ip"//ipコマンドの時...
            -> {
                /////args[0]  つまり　/ip <ここ> があるかどうか判断します。
                try {
                    if (args[0] != null) {
                    }
                } catch (e: ArrayIndexOutOfBoundsException) {
                    println("args0がないです")
                    return false//終了
                }
                //////
                val playerx = this.server.getPlayer(args[0])//player
                if (playerx !is Player) {///プレイヤーかどうか
                    sender.sendMessage(TextFormat.GREEN.toString() + "プレイヤーの名前を入れましょう")
                } else {
                    sender.sendMessage(TextFormat.GREEN.toString() + "プレイヤー名:" + args[0] + "  ip:" + playerx.address)
                }
                return true//終了
            }
        }
        return false//終了
    }

}
