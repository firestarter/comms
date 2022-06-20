package com.firestartermc.comms

import com.firestartermc.comms.channel.Channel
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger

@Plugin(id = "comms")
class CommsPlugin @Inject constructor(proxy: ProxyServer, private val logger: Logger) {

    private val channel = Channel(proxy, "staff", '!')

    @Subscribe
    fun chat(event: PlayerChatEvent) {
        if (event.message.first() != channel.prefix) {
            return
        }

        event.player.takeIf { it.canAccess(channel) }?.let {
            channel.broadcast(it, event.message.drop(1))
            event.result = PlayerChatEvent.ChatResult.denied()
        }
    }
}
