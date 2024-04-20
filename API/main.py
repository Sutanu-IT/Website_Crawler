from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import httpx
import uvicorn
from utils import crawler, summarizer_genai, summarizer_openai

app = FastAPI()  # creating FastAPI app


class UrlItem(BaseModel):
    url: str


@app.post("/crawl/")
async def crawl_website(item: UrlItem):
    try:
        async with httpx.AsyncClient() as client:
            response = await client.get(item.url)  # fetching the response
            response.raise_for_status()
            p_text = crawler(response)  # passing the response to crawl raw text
            prompt = "summarize this given text and make markdown text with proper heading subheading and list"  # given prompt
            text = summarizer_genai(prompt, p_text)  # summarizing the text with given prompt using gemini
            # text= summarizer_openai(prompt, p_text) # summarizing the text with given prompt using openai
            return {"url": item.url, "text": text}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))


if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)
