package com.example.terdlor_first_bot.common

import com.example.terdlor_first_bot.BotApp
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity


abstract class CommandWork : Work() {

    abstract fun commandWork(msgBd : com.example.terdlor_first_bot.bd.chat.model.Message)

    override fun checkWork(msg : Message, msgBd : com.example.terdlor_first_bot.bd.chat.model.Message) : Boolean {
        if (msg.entities == null) return false
        val entity : MessageEntity? =
                msg.entities.stream().filter{ en -> en.type == "bot_command" &&
                        (en.text.equals("/$command") || en.text.equals("/$command@" + BotApp.foo)) }.findAny().orElse(null)
        if (entity != null) {
            commandWork(msgBd)
            return true
        }
        return false
    }
}