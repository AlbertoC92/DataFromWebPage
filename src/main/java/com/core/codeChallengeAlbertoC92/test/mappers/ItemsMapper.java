package com.core.codeChallengeAlbertoC92.test.mappers;

import com.core.codeChallengeAlbertoC92.test.data.DbItem;
import com.core.codeChallengeAlbertoC92.test.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemsMapper {

    public Item fromDbItemToItem(DbItem dbItem) {
        Item item = new Item();
        item.setTitle(dbItem.getTitle());
        item.setLink(dbItem.getLink());
        item.setDescription(dbItem.getDescription());
        item.setPubDate(dbItem.getPubDate());

        return item;
    }
}
