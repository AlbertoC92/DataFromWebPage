package com.core.codeChallengeAlbertoC92.test.controller;

import com.core.codeChallengeAlbertoC92.test.data.DbItem;
import com.core.codeChallengeAlbertoC92.test.model.Item;
import com.core.codeChallengeAlbertoC92.test.service.CreateItems;
import com.core.codeChallengeAlbertoC92.test.service.CreateItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ApplicationController")
public class ApplicationController {

    @Autowired
    private CreateItems createItems;
    
    @PostMapping(value = "/geItems")
    private List<DbItem> obtainItems(@RequestParam("url") String url) {
        return createItems.insertItems(url);
    }

    @GetMapping(value = "/obtainStoredItems")
    private List<Item> obtainStoredItems() {
        return createItems.obtainSortedItems();
    }
}
