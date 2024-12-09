package schwarz.it.lws.springuebung.controller

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import schwarz.it.lws.springuebung.service.ArticleService

@RestController
@RequestMapping("/api/articles")
class ArticleController (private val articleService: ArticleService) {

    @GetMapping
    fun getArticles() = articleService.getAllArticle()


}