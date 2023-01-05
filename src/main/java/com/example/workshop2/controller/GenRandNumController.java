package com.example.workshop2.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.workshop2.exception.RandomNumException;
import com.example.workshop2.models.Generate;

// annotate as controller
@Controller
// requestMapping is also a GET Method use for Class Level 
@RequestMapping(path = "/rand")
public class GenRandNumController {
    // http://localhost:8080/rand/show will display generate.html
    @GetMapping (path = "/show")
    // Model is use to pass value g to view page 
    public String showRandForm(Model model) {
        // instantiate the generate object
        Generate g = new Generate();
        // need to addAttribute() to bind to view page 
        // generateObj is the attribute name, g is the value we want to pass to view page
        model.addAttribute("generateObj", g);
        return "generate"; // need to create a template called generate.html
    }

    // when we submit a form we use PostMapping 
    @PostMapping (path = "/generate")
    // bind form data with generate 
    public String postRandNum(@ModelAttribute Generate generate, Model model) {
        this.randomizeNum(model,generate.getNumberVal());
        return "result"; // need to create a template called result.html
    }

    // randomizeNum() is the business logic 
    private void randomizeNum(Model model,int noOfGenerateNo){
        // maximum number we generate cannot be more than 30
        int maxGenNo = 30;
        String[] imgNumbers = new String[maxGenNo+1];

        // Validate only accept greater than 0 less than equal to 30 in the form field
        if(noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo){
            throw new RandomNumException();
        }

        // generate all the number images store it 
        // to the imgNumbers array 
        for(int i =0 ; i < maxGenNo+1; i++){
            imgNumbers[i] = "number" + i + ".jpg";
        }

        List<String> selectedImg = new ArrayList<String>();
        Random rnd = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>();
        while(uniqueResult.size() < noOfGenerateNo){
            Integer resultOgfRand = rnd.nextInt(maxGenNo);
            uniqueResult.add(resultOgfRand);
        }

        Iterator<Integer> it = uniqueResult.iterator();
        Integer currElem = null;
        while(it.hasNext()){
            currElem = it.next();
            selectedImg.add(imgNumbers[currElem.intValue()]);
        }

        model.addAttribute("numberRandomNum", noOfGenerateNo);
        model.addAttribute("randNumResult", selectedImg.toArray());
    }
}
