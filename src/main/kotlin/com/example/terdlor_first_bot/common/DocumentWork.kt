package com.example.terdlor_first_bot.common

import com.example.terdlor_first_bot.BotApp
import org.telegram.telegrambots.meta.api.objects.Document
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity


abstract class DocumentWork : Work() {

    abstract fun commandWork(msgBd : com.example.terdlor_first_bot.bd.chat.model.Message, doc : Document)

    override fun checkWork(msg : Message, msgBd : com.example.terdlor_first_bot.bd.chat.model.Message) : Boolean {
        if (msg.document == null) return false
        if (msg.captionEntities == null) return false
        val entity : MessageEntity? =
                msg.captionEntities.stream().filter{ en -> en.type == "bot_command" &&
                        (en.text.equals("/$command") || en.text.equals("/$command@" + BotApp.foo)) }.findAny().orElse(null)
        if (entity != null) {
            commandWork(msgBd, msg.document)
            return true
        }
        return false
    }
}