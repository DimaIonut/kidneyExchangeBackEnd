package com.kidneyExchange.controller;

import com.kidneyExchange.Entity.FinalPair;
import com.kidneyExchange.service.StartAlgorithmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StartAlgorithmController {

  @Autowired
  private StartAlgorithmService startAlgorithmService;

  @RequestMapping(value = "/start_find_compatibility", method = RequestMethod.GET)
  public List<FinalPair> startAlgorithm() {

    return startAlgorithmService.startAlgorithm();
  }
}
