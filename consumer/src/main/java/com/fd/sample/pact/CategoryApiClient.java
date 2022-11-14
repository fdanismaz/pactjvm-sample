package com.fd.sample.pact;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("provider")
public interface CategoryApiClient {

    @GetMapping("/category")
    List<Category> listCategories();
}
