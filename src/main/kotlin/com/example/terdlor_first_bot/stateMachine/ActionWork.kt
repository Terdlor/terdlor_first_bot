package com.example.terdlor_first_bot.stateMachine

import org.springframework.statemachine.StateContext
import org.springframework.statemachine.action.Action

class ActionWork : Action<States, Events> {

    override fun execute(p0: StateContext<States, Events>?) {
        if (p0 != null) {
            p0.extendedState.variables.forEach {
                com.example.terdlor_first_bot.utils.println("${it.key} - ${it.value}")
            }
            com.example.terdlor_first_bot.utils.println("move from " + p0.stateMachine.state.id + ", event " + p0.message.payload)
        }
    }
}