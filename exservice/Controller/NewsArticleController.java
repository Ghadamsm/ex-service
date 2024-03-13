package com.example.exservice.Controller;


import com.example.exservice.API.ApiResponse;
import com.example.exservice.Model.NewsArticle;
import com.example.exservice.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {


   private final NewsArticleService newsArticleService ;



   @GetMapping("/get")
   public ResponseEntity getNewsArticle(){
       return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
   }


   @PostMapping("/add")
   public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle , Errors errors){

       if (errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
       }

       newsArticleService.addNews(newsArticle);
       return ResponseEntity.status(200).body(new ApiResponse("added"));
   }



   @PutMapping("/update/{id}")
   public ResponseEntity updateNews(@PathVariable int id , @RequestBody @Valid NewsArticle newsArticle , Errors errors){

       if (errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
       }

       boolean isUpdate = newsArticleService.updateNews(id , newsArticle);
       if (isUpdate){
           return ResponseEntity.status(200).body(new ApiResponse("updated"));
       }

       return ResponseEntity.status(400).body(new ApiResponse("Invalid"));
   }



   @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteNews(@PathVariable int id ){

       boolean isFound = newsArticleService.deleteNews(id);
       if (isFound){
           return ResponseEntity.status(200).body(new ApiResponse("deleted"));
       }

       return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
   }



   @GetMapping("publishN/{id}")
   public ResponseEntity publishNews(@PathVariable int id){

       if (newsArticleService.publish(id) == null){
           return ResponseEntity.status(400).body(new ApiResponse("invalid ID"));
       }

       return ResponseEntity.status(200).body(new ApiResponse(id + " published!"));

   }



   @GetMapping("/published")
   public ResponseEntity getAllPublished(){
       if (newsArticleService.publishNews() == null){
           return ResponseEntity.status(400).body(new ApiResponse("there is no published newsArticles"));
       }
       return ResponseEntity.status(200).body(newsArticleService.publishNews());
   }



   @GetMapping("/gatAllC/{category}")
   public ResponseEntity searchByCategory(@PathVariable String category ){

       if (newsArticleService.getByCategory(category) == null){
           return ResponseEntity.status(400).body(new ApiResponse("cannot found"));
       }
       return ResponseEntity.status(200).body(newsArticleService.getByCategory(category));

   }


}
