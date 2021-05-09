package com.twm.aasimplelucendemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LuceneController {

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam("search") String searchItem){



        IndexQuery indexQuery = new IndexQuery();
        String ans="";
        try {
            ans = indexQuery.query(searchItem);
        }catch (Exception e){
            System.out.println("some error");
        }

        return ans;
    }

}
