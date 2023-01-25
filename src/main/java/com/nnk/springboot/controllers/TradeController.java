package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class TradeController {
    // TODO: Inject Trade service OK
    @Autowired
    private TradeService tradeService;

    Logger log = LoggerFactory.getLogger(RatingController.class);


    @RequestMapping("/trade/list")
    public String home(Model model, Principal user)
    {
        // TODO: find all Trade, add to model OK
        List<Trade> tradeList = tradeService.findAll();
        log.info("Find all Trade SUCCESS");
        model.addAttribute("tradeList", tradeList);
        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
        }
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        log.info("Add User SUCCESS");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list OK
        if(!result.hasErrors()){
            tradeService.save(trade);
            model.addAttribute("tradeList",tradeService.findAll());
            log.info("Validate Data in DB SUCCESS");
            return "redirect:/trade/list";
        }
        log.error("Validate Data in DB FAILED");
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form OK
        Trade trade = tradeService.findById(id).orElseThrow(()->new IllegalArgumentException("Ivalide trade id: " + id));
        model.addAttribute("trade", trade);
        log.info("Get trade by Id SUCCESS");
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list OK
        if(result.hasErrors()){
            log.error("Update Trade FAILED");
            return "redirect:/trade/update";
        }
        trade.setTradeId(id);
        tradeService.save(trade);
        model.addAttribute("tradeList",tradeService.findAll());
        log.info("Update trade SUCCESS");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list OK
        Trade trade = tradeService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid trade Id : " + id));
        tradeService.delete(trade);
        model.addAttribute("trade", trade);
        log.info("Delete Trade SUCCESS");
        return "redirect:/trade/list";
    }
}
