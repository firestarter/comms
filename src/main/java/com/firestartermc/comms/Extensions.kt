package com.firestartermc.comms

import com.firestartermc.comms.channel.Channel
import com.velocitypowered.api.proxy.Player

fun Player.canAccess(channel: Channel): Boolean {
    return this.hasPermission("channel.${channel.id}")
}

val Player.serverName: String
    get() = this.currentServer.orElse(null)?.serverInfo?.name ?: "???"