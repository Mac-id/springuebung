package schwarz.it.lws.springuebung.service

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.*
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.repository.ArticleRepository
import java.util.*


class ArticleServiceTest : FunSpec({
    val articleRepository = mockk<ArticleRepository>()
    val articleService = ArticleService(articleRepository)

    test("should get all articles from service") {
        every { articleRepository.findAll() } returns listOf(
            Article(1, "a1", 1.0),
            Article(2, "a2", 2.0),
            Article(3, "a3", 3.0),
        )

        val articles = articleService.getAllArticle()

        articles shouldNotBe null
        articles.size shouldBe 3
        verify {
            articleRepository.findAll()
        }
    }

    test("should delete article from service") {
        val id: Long = 1

        every { articleRepository.deleteById(id) } just runs

        every { articleRepository.existsById(id) } returns true

        articleService.deleteArticleById(id)

        verify {
            articleRepository.deleteById(id)
        }
    }

    test("should add article") {
        val article = Article(0, "new", 5.0)

        every { articleRepository.save(article) } returns Article(1, "new", 5.0)

        val createdArticle = articleService.addArticle(article)
        createdArticle.id shouldBe 1
    }

    test("should update article") {
        val article = Article(1, "new", 5.0)

        every { articleRepository.save(article) } returns article
        every { articleRepository.existsById(article.id) } returns true

        val updatedArticle = articleService.updateArticle(article)
        verify {
            articleRepository.existsById(1)
            articleRepository.save(article)
        }
    }

    test("should find articles by title"){
        val title  ="title"

        every { articleRepository.findByTitle(title) } returns mutableListOf(
            Article(1, "new", 5.0),
            Article(2, "new", 5.0),
            Article(3, "new", 5.0)
        )

        val articles = articleService.getArticlesByTitle(title)


        articles.size shouldBe 3
    }
})