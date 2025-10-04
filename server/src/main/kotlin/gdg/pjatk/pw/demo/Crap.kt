package gdg.pjatk.pw.demo

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor

fun getAgentOrMessage(): Any {
    val apiKey = System.getProperty("GEMINI_API_KEY")

    return if (apiKey.isNullOrBlank()) {
        "There is no API key"
    } else {
        AIAgent(
            promptExecutor = simpleGoogleAIExecutor(apiKey),
            llmModel = GoogleModels.Gemini2_5Pro
        )
    }
    }

