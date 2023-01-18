package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RuleNameController {
    // TODO: Inject RuleName service OK
    @Autowired
    private RuleNameService ruleNameService;

    Logger log = LoggerFactory.getLogger(RatingController.class);


    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        // TODO: find all RuleName, add to model OK
        List<RuleName> findAllRuleName = ruleNameService.findAll();
        log.info("Find All ruleName SUCCESS");
        model.addAttribute("ruleNameList", findAllRuleName);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        log.info("Add Rule SUCCESS");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list OK
        if (!result.hasErrors()) {
            ruleNameService.save(ruleName);
            model.addAttribute("ruleNameList", ruleNameService.findAll());
            log.info("Validate Data in DB SUCCESS");
            return "redirect:/ruleName/list";
        }

        log.error("Validate Rule Name in DB FAILED");
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form OK
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Rule id: " + id));
        model.addAttribute("ruleName", ruleName);
        log.info("Find ruleName by ID SUCCESS");
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list OK
        if(result.hasErrors()){
            log.error("Update Rule Name FAILED");
            return "redirect:/ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.save(ruleName);
        model.addAttribute("ruleNameList", ruleNameService.findAll());
        log.info("Update Rule Name SUCCESS");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list OK
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Rule id: " + id));
        ruleNameService.delete(ruleName);
        model.addAttribute("ruleNameList", ruleName);
        log.info("Delete Rule Name SUCCESS");
        return "redirect:/ruleName/list";
    }
}
