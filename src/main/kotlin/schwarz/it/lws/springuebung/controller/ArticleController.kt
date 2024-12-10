package schwarz.it.lws.springuebung.controller

import org.springframework.web.bind.annotation.*
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.service.ArticleService

@RestController
@RequestMapping("/api/articles")
class ArticleController(private val articleService: ArticleService) {

    @GetMapping
    fun getArticles() = articleService.getAllArticle()

    @GetMapping("/{id}")
    fun getArticleById(@PathVariable id: Long) = articleService.getArticleById(id)

    @GetMapping("/title/{title}")
    fun getArticleById(@PathVariable title: String) = articleService.getArticlesByTitle(title)

    @PostMapping
    fun postArticle(@RequestBody article: Article) = articleService.addArticle(article)

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long) = articleService.deleteArticleById(id)

    @PatchMapping
    fun updateArticle(@RequestBody article: Article) = articleService.updateArticle(article)


}