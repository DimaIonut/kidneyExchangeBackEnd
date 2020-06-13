package com.kidneyExchange.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

@Service
public class CompatibilityRules {

  private Map<String, List<String>> compatibilityRules = new HashMap<>();

  private List<String> RULE_0 = Arrays.asList("0", "A", "B", "AB");

  private List<String> RULE_A = Arrays.asList("A", "AB");

  private List<String> RULE_B = Arrays.asList("B", "AB");

  private List<String> RULE_AB = Arrays.asList("AB");

  public void createRules() {

    compatibilityRules.put("0", this.RULE_0);
    compatibilityRules.put("A", this.RULE_A);
    compatibilityRules.put("B", this.RULE_B);
    compatibilityRules.put("AB", this.RULE_AB);
  }

  public Map<String, List<String>> getCompatibilityRules() {
    return compatibilityRules;
  }
}