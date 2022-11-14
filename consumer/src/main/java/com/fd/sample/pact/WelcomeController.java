package com.fd.sample.pact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    CategoryApiClient categoryApiClient;

    @GetMapping("/category")
    public String getCategories() {
        String aggregatedCategories = categoryApiClient.listCategories().stream()
                .map(category -> category.getName())
                .reduce("", (left, right) -> left + "," + right);
        return aggregatedCategories.substring(1);
    }

}
