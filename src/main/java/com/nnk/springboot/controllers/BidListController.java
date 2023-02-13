package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class BidListController {
    // TODO: Inject Bid service OK
    @Autowired
    private BidListService bidListService;


    Logger log = LoggerFactory.getLogger(BidListController.class);



    @RequestMapping("/bidList/list")
        public String home(Model model, Principal user){
        // TODO: call service find all bids to show to the view OK
        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidlists", bidLists);
        log.info("Find all bids SUCCESS");
        if(user instanceof OAuth2AuthenticationToken){
            model.addAttribute("username", ((OAuth2AuthenticationToken) user).getPrincipal().getAttributes().get("login"));
        }
        else if(user instanceof UsernamePasswordAuthenticationToken){
            model.addAttribute("username", user.getName());
        }

        return "bidList/list";
    }
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("Add BidList SUCCESS");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list OK
        if(!result.hasErrors()){
            bidListService.save(bid);
            model.addAttribute("bidlists", bidListService.findAll());
            log.info("Save BidList to DB, SUCCESS");
            return "redirect:/bidList/list";
        }
        log.error("Validate BidList FAILED");
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form OK (?)
        BidList bidList = bidListService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        log.info("Get Bid by ID SUCCESS");
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid OK
        if(result.hasErrors()){
            log.error("Update BidList FAILED");
            return "bidList/update";
        }
        bidList.setId(id);
        bidListService.save(bidList);
        model.addAttribute("bidlists", bidListService.findAll());
        log.info("Update BidList SUCCESS");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list OK
        BidList bidListbyId =bidListService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));;
        bidListService.delete(bidListbyId);
        model.addAttribute("bidList", bidListbyId);
        log.info("Delete BidList SUCCESS");
        return "redirect:/bidList/list";
    }

}
