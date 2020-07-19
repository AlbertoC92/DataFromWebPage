package com.core.codeChallengeAlbertoC92.test.service;

import com.core.codeChallengeAlbertoC92.test.Exceptions.EmptyDataBaseException;
import com.core.codeChallengeAlbertoC92.test.Exceptions.ItemAlreadyExistException;
import com.core.codeChallengeAlbertoC92.test.data.DbItem;
import com.core.codeChallengeAlbertoC92.test.mappers.ItemsMapper;
import com.core.codeChallengeAlbertoC92.test.model.Item;
import com.core.codeChallengeAlbertoC92.test.repository.ItemRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings(value = {"rawtypes"})
public class CreateItemsService implements CreateItems {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateItemsService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemsMapper itemsMapper;

    @SneakyThrows
    private String peticionHttpGet(String urlParaVisitar) {
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urlParaVisitar);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);

        }
        rd.close();
        return resultado.toString();
    }

    @SneakyThrows
    @Override
    public List<DbItem> insertItems(String urlParaVisitar) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(peticionHttpGet(urlParaVisitar)));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("item");
        List<DbItem> dbItemList = new ArrayList<DbItem>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            DbItem dbItem = createItem(element);
            DbItem exist = itemRepository.findItemByTitle(dbItem.getTitle());
            if (exist != null) {
                String longMessage = "The item already exist into the data base";
                throw new ItemAlreadyExistException("The item you are trying to insert, is already in the data base", HttpStatus.BAD_REQUEST,longMessage, LOGGER);
            }
            itemRepository.save(dbItem);
            dbItemList.add(dbItem);
        }
        return dbItemList;
    }

    @SneakyThrows
    private DbItem createItem(Element element) {

        DbItem item = new DbItem();

        NodeList titleXml = element.getElementsByTagName("title");
        Element line = (Element) titleXml.item(0);
        String title = getCharacterDataFromElement(line);
        item.setTitle(title);

        NodeList linkXml = element.getElementsByTagName("link");
        line = (Element) linkXml.item(0);
        String link = getCharacterDataFromElement(line);
        item.setLink(link);

        NodeList descriptionXml = element.getElementsByTagName("description");
        line = (Element) descriptionXml.item(0);
        String description = getCharacterDataFromElement(line);
        item.setDescription(description);

        NodeList enclosureXml = element.getElementsByTagName("enclosure");
        line = (Element) enclosureXml.item(0);
        String enclosure = getCharacterDataFromElement(line);
        item.setEnclosure(enclosure);

        NodeList pubDateXml = element.getElementsByTagName("pubDate");
        line = (Element) pubDateXml.item(0);
        String pubDate = getCharacterDataFromElement(line);
        item.setPubDate(pubDate);

        NodeList guidXml = element.getElementsByTagName("guid");
        line = (Element) guidXml.item(0);
        String guid = getCharacterDataFromElement(line);
        item.setGuid(guid);

        NodeList feedBurnerXml = element.getElementsByTagName("feedburner:origLink");
        line = (Element) feedBurnerXml.item(0);
        String feedBurner = getCharacterDataFromElement(line);
        item.setFeedBurner(feedBurner);

        return item;
    }

    @SneakyThrows
    private String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    @SneakyThrows
    @Override
    public List<Item> obtainSotredItems() {
        List<DbItem> dbItemList = itemRepository.findAll();
        if(dbItemList.isEmpty()) {
            String longMessage = "The data base doesn't have any items to check";
            throw new EmptyDataBaseException("The data base is empty", HttpStatus.BAD_REQUEST,longMessage, LOGGER);
        }
        List<Item> itemList = new ArrayList<Item>();
        dbItemList.forEach(dbItem -> itemList.add(itemsMapper.fromDbItemToItem(dbItem)));

        return itemList;
    }
}
