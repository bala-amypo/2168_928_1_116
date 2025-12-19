@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    public BreachRuleController(BreachRuleService breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public String createRule(@RequestBody BreachRule breachRule) {
        breachRuleService.createRule(breachRule);
        return "Breach rule created";
    }

    @PutMapping("/{id}")
    public String updateRule(@PathVariable Long id,
                             @RequestBody BreachRule breachRule) {
        breachRuleService.updateRule(id, breachRule);
        return "Breach rule updated";
    }

    @GetMapping("/{id}")
    public BreachRule getRule(@PathVariable Long id) {
        return breachRuleService.getRuleById(id);
    }

    @GetMapping
    public List<BreachRule> getAllRules() {
        return breachRuleService.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateRule(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
        return "Breach rule deactivated";
    }
}
