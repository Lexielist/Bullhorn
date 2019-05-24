package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class Homecontroller {

    @Autowired
    MsgRepository msgRepository;

    @RequestMapping("/")
    public String listMsgs(Model model){
        model.addAttribute("msgs", msgRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String msgform(Model model){
        model.addAttribute("msg", new Msg());
        return "msgform";

    }
    @PostMapping("/process")
    public String processForm(@Valid Msg msg, BindingResult result){
        if (result.hasErrors()){
            return "msgform";

        }
        msgRepository.save(msg);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMsg(@PathVariable("id") Long id, Model model) {

        model.addAttribute("msg", msgRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMsg(@PathVariable("id") Long id, Model model){
        model.addAttribute("msg", msgRepository.findById(id).get());
        return "msgform";
    }
    @RequestMapping("/delete/{id}")
    public String delMsg(@PathVariable("id") Long id){
        msgRepository.deleteById(id);
        return "redirect:/";
    }

}



