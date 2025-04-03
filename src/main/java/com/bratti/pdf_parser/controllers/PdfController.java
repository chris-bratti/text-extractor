package com.bratti.pdf_parser.controllers;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
public class PdfController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/extract")
    public ResponseEntity<String> extractText(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        if (!Objects.equals(file.getContentType(), "application/pdf")) {
            return ResponseEntity.badRequest().body("Only PDF files are allowed");
        }

        try {
            byte[] pdfBytes = file.getBytes();
            try (PDDocument document = Loader.loadPDF(new RandomAccessReadBuffer(pdfBytes)))
            {
                PDFTextStripper textStripper = new PDFTextStripper();
                String text = textStripper.getText(document);
                text = text.replace("\n", " ");
                return ResponseEntity.ok(text);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to extract text from PDF");
        }
    }


}
