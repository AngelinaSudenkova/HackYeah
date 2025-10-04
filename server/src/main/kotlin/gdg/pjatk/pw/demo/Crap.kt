package gdg.pjatk.pw.demo

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.google.GoogleModels
import ai.koog.prompt.executor.llms.all.simpleGoogleAIExecutor

val agent = AIAgent(
    promptExecutor = simpleGoogleAIExecutor(System.getProperty("geminiApiKey")),
    llmModel = GoogleModels.Gemini2_5Pro
)