package com.haniokasai.nukkitkt.libPVP.tools

import cn.nukkit.blockentity.BlockEntitySign
import cn.nukkit.math.Vector3
import cn.nukkit.blockentity.BlockEntity
import cn.nukkit.Player
import cn.nukkit.Server
import cn.nukkit.event.EventHandler
import cn.nukkit.event.player.PlayerInteractEvent
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import com.haniokasai.nukkitkt.libPVP.Main


/**
 * Created by hani on 2017/02/11.
 */

class signcommand(internal var plugin: Main) : Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    fun PlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        if (event.block.id != 63 && event.block.id != 68) return
        val blockEntity = event.block.getLevel().getBlockEntity(Vector3(event.block.getX(), event.block.getY(), event.block.getZ())) as? BlockEntitySign ?: return
        if (blockEntity.text[0].equals("[Command]", ignoreCase = true)) {
            Server.getInstance().dispatchCommand(player, blockEntity.text[3])
            event.isCancelled = true
        }

    }
}