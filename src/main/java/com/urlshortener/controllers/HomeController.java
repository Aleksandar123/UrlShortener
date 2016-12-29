package com.urlshortener.controllers;

import com.urlshortener.models.Links;
import com.urlshortener.services.LinksService;
import com.urlshortener.services.NotificationService;
import com.urlshortener.tools.ShortUrlUtil;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by aleksandar on 28.12.16.
 */
@Controller
public class HomeController {

    @Autowired
    private LinksService linksService;

    @Autowired
    NotificationService notificationService;

    private ShortUrlUtil suu = new ShortUrlUtil();


    @ModelAttribute("links")
    public Links tweet(){
        return new Links();
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/{shortUrl}")
    public String shortUrl(@PathVariable("shortUrl")String shortUrl, Model model){
        Links link = linksService.getByShortUrl(shortUrl);
        if(link!=null) {
            model.addAttribute("link", link);
            return "s";
        }else{
            notificationService.addErrorMessage("Short link not found!");
            return "redirect:/";
        }

    }

    @RequestMapping("/preview/{shortUrl}")
    public String previewUrl(@PathVariable("shortUrl")String shortUrl, Model model){
        Links link = linksService.getByShortUrl(shortUrl);
        if(link!=null){
        model.addAttribute("link",link);
        return "preview";
        }else{
            notificationService.addErrorMessage("Link not found!");
            return "redirect:/";
        }
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newShortUrl(@ModelAttribute Links links, BindingResult bindingResult,Model model){
        UrlValidator urlValidator = new UrlValidator();
        Links lnk = linksService.getByLongUrl(links.getLongUrl());

        if(bindingResult.hasErrors()){
            notificationService.addErrorMessage("The entered url is not valid.");
            return "redirect:/";

        } else{

          if(urlValidator.isValid(links.getLongUrl())) {

              if(lnk==null){
                  links.setShortUrl(suu.randomString());
                  linksService.create(links);
                  notificationService.addInfoMessage("Short link created.");
                  return "redirect:/preview/" + links.getShortUrl();
              }else {
                  notificationService.addInfoMessage("Shorter link already exists.");
                  lnk = linksService.getByLongUrl(links.getLongUrl());
                  return "redirect:/preview/" + lnk.getShortUrl();
              }


          }else
          {
              //bindingResult.rejectValue("longUrl", "error.longUrl", "This isn't a valid url.");
              notificationService.addErrorMessage("The entered url is not valid.");
          }
        }
        //return "redirect:/preview/" + links.getShortUrl();
        return "redirect:/";
    }

}
