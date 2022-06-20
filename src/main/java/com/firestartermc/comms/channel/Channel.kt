package com.firestartermc.comms.channel

import com.firestartermc.comms.canAccess
import com.firestartermc.comms.serverName
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.audience.MessageType
import net.kyori.adventure.text.Component.space
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor

class Channel(private val proxy: ProxyServer, val id: String, val prefix: Char) {

    private val members get() = proxy.allPlayers.filter { it.canAccess(this) }

    fun broadcast(source: Player, message: String) {
        val component = text()
            .append(LEFT_BRACKET)
            .append(text("SC", NamedTextColor.RED))
            .append(SLASH)
            .append(text(source.serverName, NamedTextColor.RED))
            .append(RIGHT_BRACKET)
            .append(space())
            .append(text("${source.username}:", NamedTextColor.RED))
            .append(space())
            .append(text(message, NamedTextColor.RED))
            .build()

        members.forEach { it.sendMessage(it, component, MessageType.SYSTEM) }
    }

    private companion object {
        val LEFT_BRACKET = text("[", NamedTextColor.DARK_GRAY)
        val RIGHT_BRACKET = text("]", NamedTextColor.DARK_GRAY)
        val SLASH = text("/", NamedTextColor.DARK_GRAY)
    }
}
