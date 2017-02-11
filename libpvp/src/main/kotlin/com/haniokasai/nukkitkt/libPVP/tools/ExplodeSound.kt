package com.haniokasai.nukkitkt.libPVP.tools

/**
 * Created by hani on 2017/02/11.
 */

import cn.nukkit.level.sound.GenericSound
import cn.nukkit.math.Vector3
import cn.nukkit.network.protocol.LevelEventPacket

class ExplodeSound @JvmOverloads constructor(pos: Vector3, pitch: Float = 0f) : GenericSound(pos, LevelEventPacket.EVENT_SOUND_CAULDRON, pitch)