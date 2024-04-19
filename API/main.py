from bs4 import BeautifulSoup
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import httpx
import uvicorn
import google.generativeai as genai
from dotenv import load_dotenv
import os
load_dotenv()


genai.configure(api_key=os.getenv('GOOGLE_API_KEY'))
model = genai.GenerativeModel('gemini-pro')


app = FastAPI()


class UrlItem(BaseModel):
    url: str


@app.post("/crawl/")
async def crawl_website(item: UrlItem):
    try:
        async with httpx.AsyncClient() as client:
            response = await client.get(item.url)
            response.raise_for_status()
            soup = BeautifulSoup(response.text, 'html.parser')
            # Extract all text from the webpage
            p_text = soup.get_text(separator=' ', strip=True)

            text = model.generate_content("summarize this given text and make markdown text with proper heading subheading and list"+p_text).text
            return {"url": item.url, "text": text}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)
