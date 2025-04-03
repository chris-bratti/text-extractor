# TextExtractor

### A simple Spring Boot API that extracts text from a given PDF using Apache PDFBox. Used in the ParseCV project

One of the most important parts of the resume parsing process is a library for accurately extracting text from a PDF. Unfortunately, none of the Python libraries I tried were able to consistently handle the formatting
that is typically seen in resumes. I decided to try an option outside of Python, and Apache PDFBox was able to give me the most consistent results. I decided to move the logic of text extraction to this Spring Boot API
to get the best results possible.