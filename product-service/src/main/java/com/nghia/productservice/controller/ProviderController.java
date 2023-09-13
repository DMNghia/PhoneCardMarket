package com.nghia.productservice.controller;

import java.io.IOException;
import java.util.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/provider")
public class ProviderController {

  @PostMapping("/upload")
  public ModelAndView uploadImage(@RequestParam("file") MultipartFile file)
      throws IOException {
    byte[] bytes = file.getBytes();
    System.out.println(file.getContentType());
    ModelAndView mav = new ModelAndView("upload");

    String encode = Base64.getEncoder().encodeToString(bytes);
    mav.addObject("code", "data:image/png;base64," + encode);

    return mav;
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<?> handleIOException(IOException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
