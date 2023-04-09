package com.example.toyproject.controller;

import com.example.toyproject.dto.BoardSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
public class BookController {

    // 네이버 도서 검색 api key
    private final String CLIENT_ID = "22MWQam8qBycNFDUAGCp";
    private final String CLIENT_SECRET = "r84H3YH0Ba";

    @GetMapping("/board/book-search")
    public String home(Model model) {
        String keyword = "";
        model.addAttribute("keyword", keyword);
        return "form/board/book-search";
    }

    @PostMapping("/board/book-search")
    public String search(@ModelAttribute("keyword") String keyword, Model model) {
        try {
            Document doc = Jsoup.connect("https://openapi.naver.com/v1/search/book.json?query=" + URLEncoder.encode(keyword, "UTF-8"))
                    .header("X-Naver-Client-Id", CLIENT_ID)
                    .header("X-Naver-Client-Secret", CLIENT_SECRET)
                    .ignoreContentType(true)
                    .get();

            JSONObject jsonObject = new JSONObject(doc.text());
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            List<BoardSearchDto> boardSearchDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String title = obj.getString("title");
                String author = obj.getString("author");
                String image = obj.getString("image");

                boardSearchDtoList.add(BoardSearchDto.builder()
                        .title(title)
                        .creator(author)
                        .imageURL(image).build());

                System.out.println("TITLE = " + title);
                System.out.println("AUTHOR = " + author);
                System.out.println("IMAGE_URL = " + image);
            }
            model.addAttribute("boardSearchDtoList", boardSearchDtoList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "form/board/book-search";
    }
}

