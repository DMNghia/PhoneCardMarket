package com.nghia.productservice.controller.mvn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProviderMVCController {

  @GetMapping("/upload")
  public String upload() {
    return "upload";
  }
}
