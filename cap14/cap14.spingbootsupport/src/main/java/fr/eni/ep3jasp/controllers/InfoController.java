package fr.eni.ep3jasp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
  @GetMapping
  public String info() {
    return "Hola";
  }
}