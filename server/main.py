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


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.post("/chat")
def ask_ai(messages: list[Message]):
    print(messages)
    response = client.models.generate_content(model="gemini-2.5-flash", contents=messages[0].content, config=config)
    tool_call = response.candidates[0].content.parts[0].function_call
    if tool_call.name == "get_place_suggestions":
        response = get_place_suggestions(**tool_call.args)
    return response