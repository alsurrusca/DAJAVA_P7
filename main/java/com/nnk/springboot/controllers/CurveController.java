package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {
    // TODO: Inject Curve Point service OK

    @Autowired
    private CurvePointService curveService;

    Logger log = LoggerFactory.getLogger(CurveController.class);

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model OK
        List<CurvePoint> findAllCurve = curveService.findAll();
        log.info("Find all curve point");
        model.addAttribute("curvePointList", findAllCurve);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        log.info("Add curve Point SUCCESS");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list OK
        if(!result.hasErrors()){
            curveService.save(curvePoint);
            model.addAttribute("curvePointList", curveService.findAll());
            log.info("Save curve Point to DB, SUCCESS");
            return "redirect:/curvePoint/list";
        }
        log.error("Validate BidList in DB FAILED");
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form OK
        CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        log.info("Get Curve Point by ID SUCCESS");
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list OK
        if(result.hasErrors()){
            log.error("Update CurvePoint FAILED");
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curveService.save(curvePoint);
        model.addAttribute("curvePointList", curveService.findAll());
        log.info("Update curvePoint SUCCESS");

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list OK
        CurvePoint curvePoint = curveService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid curvePoint Id: " + id));
        curveService.delete(curvePoint);
        log.info("Delete Curve Point Success");
        model.addAttribute("curvePoint", curvePoint);
        return "redirect:/curvePoint/list";
    }
}
