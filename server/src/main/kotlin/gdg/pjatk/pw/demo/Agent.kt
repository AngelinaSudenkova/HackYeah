package gdg.pjatk.pw.demo

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor

fun getAgent(): AIAgent<String, String> {
    val apiKey = System.getProperty("GEMINI_API_KEY")

    return AIAgent(
        promptExecutor = simpleGoogleAIExecutor(apiKey),
        llmModel = GoogleModels.Gemini2_5Pro
    )
}

