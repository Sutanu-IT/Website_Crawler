import google.generativeai as genai
import openai
from bs4 import BeautifulSoup
from dotenv import load_dotenv
import os

load_dotenv()  # Loading the environment variables from .env file

openai.api_key = os.getenv('OPENAI_API_KEY')  # Set your OpenAI API key

genai.configure(api_key=os.getenv('GOOGLE_API_KEY'))  # Configuring the API key
model = genai.GenerativeModel('gemini-pro')  # Initializing the GenerativeModel


def crawler(response):
    soup = BeautifulSoup(response.text, 'html.parser')  # Extract all text from the webpage
    p_text = soup.get_text(strip=True)
    return p_text


def summarizer_genai(prompt, p_text):
    response = model.generate_content(prompt + p_text)  # summarizing the text with given prompt
    text = response.text
    return text


def summarizer_openai(prompt, p_text):
    # Generate the summary using the OpenAI GPT-3 model
    response = openai.Completion.create(
        engine="davinci",  # Specifies the GPT-3 model to be used.
        prompt=prompt + p_text,
        max_tokens=150,
        temperature=0.7
    )
    text = response.choices[0].text.strip()  # Extracts the generated text from the API response.
    return text
