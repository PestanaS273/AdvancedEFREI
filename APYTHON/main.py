from fastapi import FastAPI, UploadFile, File, BackgroundTasks
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware

import excel_analysis 
import os

app = FastAPI()

origins = [
    "http://localhost:5173",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.post("/analyze-excel")
async def analyze_excel(background_task: BackgroundTasks, file: UploadFile = File(...)):
    file_path = f"./uploads/{file.filename}"
    with open(file_path, "wb") as buffer:
        buffer.write(await file.read())

    background_task.add_task(excel_analysis.run_analysis, file_path)

    return JSONResponse(content={"status": "Analysis Started"}, status_code=200)

@app.get("/status")
def get_status():
    return JSONResponse(content={"status": excel_analysis.get_status()}, status_code=200)