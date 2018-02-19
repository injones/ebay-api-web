package com.example.demo.service;

import com.example.demo.dto.EbayCategory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by JonesIN on 29/01/2018.
 */
@Service
public class EbayCat {

    public void getCat() throws IOException {
        ArrayList<String> ui = new ArrayList<>();
        System.out.println(ui.size());
        Document doc = Jsoup.connect("https://www.isoldwhat.com/getcats/").get();
        Elements li = doc.select("#catnumbers ul li");
        li.stream().filter(e -> e.text().contains("#")).map(e -> {
            EbayCategory category = new EbayCategory();
            int index = e.text().indexOf("#");
            category.setName(e.text().substring(0, index-1));
            String id = e.text().substring(index+1).replace(" ", "");
            if (id.contains("(")){
                id = id.replace("(leaf)", "");
            }
            category.setId(id);
            Document document = null;
            try {
                document = Jsoup.connect("https://www.isoldwhat.com/getcats/index.php?RootID=" + id + "#" +id).get();
            } catch (IOException ex){
                ex.printStackTrace();
            }
            final String search = id;
            Optional<Element> el = document.select("#catnumbers ul li").stream().filter(c -> c.text().contains(search)).findFirst();
            category.setSubCategories(this.convert(el.get().select("li"), new ArrayList<>()));
            return category;
        })
                .forEach(System.out::println);
    }

    public void test(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.isoldwhat.com/getcats/index.php?RootID=" + "20081" + "#" +"20081").get();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        final String search = "20081";
        Optional<Element> el = document.select("#catnumbers ul li").stream().filter(c -> c.text().contains(search)).findFirst();
        Elements es = el.get().select("li");
        es.forEach(e -> {
            System.out.println(e.html());
        });
    }

    public List<EbayCategory> convert(Elements li, ArrayList<String> parId) {
        return li.stream().filter(e -> e.text().contains("#")).map(e -> {
            EbayCategory category = new EbayCategory();
            int index = e.text().indexOf("#");
            category.setName(e.text().substring(0, index-1));
            String id = e.text().substring(index+1).replace(" ", "");
            if (id.contains("(")){
                id = id.replace("(leaf)", "");
                category.setId(id);
                return category;
            }
            category.setId(id);
            Document doc = null;
            if (parId.size() == 0){
                try {
                    doc = Jsoup.connect("https://www.isoldwhat.com/getcats/index.php?RootID=" + id + "#" +id).get();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            } else if (parId.size() == 1) {
                try {
                    doc = Jsoup.connect("https://www.isoldwhat.com/getcats/index.php?RootID=" + parId.get(0) + "&L2C="
                            + id + "#" + id).get();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            } else if (parId.size() == 2){
                try {
                    doc = Jsoup.connect("https://www.isoldwhat.com/getcats/index.php?RootID=" + parId.get(0) + "&L2C="
                            + parId.get(1) + "&L3C=" + id + "#" + id).get();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
            final String search = id;
            parId.add(search);
            Optional<Element> el = doc.select("#catnumbers ul li")
                    .stream().filter(c -> c.text().contains("#" + search)).findFirst();
//            category.setSubCategories(this.convert(doc.select("#catnumbers ul li")
//                    .stream().filter(c -> c.text().contains("#" + search))
//                    .collect(Collectors.collectingAndThen(Collectors.toList(), Elements::new)), parId));
            category.setSubCategories(this.convert(el.get().select("li"), parId));
            return category;
        }).collect(Collectors.toList());
    }
}
