package com.core.codeChallengeAlbertoC92.test.service;

import com.core.codeChallengeAlbertoC92.test.data.DbItem;
import com.core.codeChallengeAlbertoC92.test.model.Item;

import java.util.List;

public interface CreateItems {
    List<DbItem> insertItems(String urlParaVisitar);
    List<Item> obtainSortedItems();
}
