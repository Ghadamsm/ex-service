package com.example.exservice.Service;


import com.example.exservice.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

        ArrayList<NewsArticle> newsArticles = new ArrayList<>();


        public ArrayList<NewsArticle> getNewsArticles(){
            return newsArticles;
        }


        public void addNews (NewsArticle newsArticle){
            newsArticles.add(newsArticle);
        }


        public boolean updateNews(int id , NewsArticle newsArticle){

            for (int i = 0; i < newsArticles.size(); i++) {
                if (newsArticles.get(i).getID() == id){
                    newsArticles.set(i , newsArticle);
                    return true ;
                }
            }
            return false ;
        }


        public Boolean deleteNews(int id){
            for (int i = 0; i < newsArticles.size(); i++) {
                if (newsArticles.get(i).getID() == id){
                    newsArticles.remove(i);
                    return true ;
                }
            }
            return false ;
        }


        public Boolean publish(int id){

            for (int i = 0; i < newsArticles.size() ; i++) {
                for (NewsArticle newsArticle : newsArticles) {
                    if (newsArticle.getID().equals(id)) {
                        if (newsArticle.getIsPublished() == false) {
                            newsArticle.setIsPublished(true);
                            newsArticle.setPublishDate(LocalDate.now());
                            return true ;
                        }
                    }
                }
            }
            return null ;
        }



    public ArrayList<NewsArticle> publishNews(){
            ArrayList<NewsArticle> publish = new ArrayList<>();
            for (NewsArticle newsArticle : newsArticles){
                    if (newsArticle.getIsPublished().equals(true)) {
                        publish.add(newsArticle);
                    }
            }
            return publish ;
    }



    public NewsArticle getByCategory(String category){

            for (NewsArticle newsArticle : newsArticles){
                if (newsArticle.getCategory().equalsIgnoreCase(category)){
                    return newsArticle ;
                }
            }
            return null ;
    }


}
