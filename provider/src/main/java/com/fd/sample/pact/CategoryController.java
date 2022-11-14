package com.fd.sample.pact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("")
    public List<Category> listCategories() {
        return Arrays.asList(
                new Category(1, "Book"),
                new Category(2, "Movie"),
                new Category(3, "Computer")
        );
    }
}
