from load_dotenv import load_dotenv
load_dotenv()
from google import genai

from fastapi import FastAPI
from pydantic import BaseModel


client = genai.Client()

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
    response = client.models.generate_content(model="gemini-2.5-flash", contents="Explain how AI works in a few words")
    return response.text