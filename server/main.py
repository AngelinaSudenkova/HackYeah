from load_dotenv import load_dotenv
load_dotenv()
from google import genai
from google.genai import types

from fastapi import FastAPI
from pydantic import BaseModel

from tools import get_place_suggestions, get_place_suggestions_declaration


client = genai.Client()
tools = types.Tool(function_declarations=[get_place_suggestions_declaration])
config = types.GenerateContentConfig(tools=[tools])

class Message(BaseModel):
    role: str
    content: str

app = FastAPI()


@app.post("/chat")
def ask_ai(messages: list[Message]):
    response = client.models.generate_content(model="gemini-2.5-flash", contents=messages[0].content, config=config)
    tool_call = response.candidates[0].content.parts[0].function_call
    if tool_call.name == "get_place_suggestions":
        response = get_place_suggestions(**tool_call.args)
    return response


@app.get("/sight")
def get_sight(sight: str):
    response = client.models.generate_content(model="gemini-2.5-flash", contents=sight, config=types.GenerateContentConfig(
        system_instruction="You are a helpful assistant that can answer questions about sights/attractions/places of interest the user is asking you about." \
        "Prefer interesting, but most importantly, factual stories to a lot of facts.",
        temperature=0.1
    ))
    return response.candidates[0].content.parts[0].text
