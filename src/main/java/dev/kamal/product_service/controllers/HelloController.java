package dev.kamal.product_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<String> helloAndWelcome() {
        String str="<h1>";
        str+="Welcome to the Matrix!";
        str+="</h1>";
        str+="<br>";
        str+="<h2>";
        str+="<mark>";
        str+="This is your last chance.";
        str+="<br>";
        str+="After this, there is no turning back.";
        str+="</mark>";
        str+="<br>";
        str+="<p style=\"color: blue;\">";
        str+="You take the blue pill - the story ends,";
        str+="<br>";
        str+="You wake up in your bed";
        str+="<br>";
        str+="And believe whatever you want to believe.";
        str+="</p>";
        str+="<p style=\"color: red;\">";
        str+="You take the red pill - ";
        str+="<br>";
        str+="You stay in Wonderland";
        str+="<br>";
        str+="And I show you how deep the rabbit hole goes.";
        str+="<br>";
        str+="</p>";
        str+="</h2>";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
