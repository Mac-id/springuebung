package schwarz.it.lws.springuebung.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.repository.ArticleRepository

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
    fun getAllArticle(): MutableList<Article> = articleRepository.findAll()

    fun getArticleById(id: Long): Article? {
        return articleRepository.findById(id).orElseThrow { EntityNotFoundException("Entity With $id not found") }
    }

    fun addArticle(article: Article) = articleRepository.save(article)

    fun deleteArticleById(id: Long) {
        if (!articleRepository.existsById(id)) {
            throw EntityNotFoundException("Article Not Found")
        }
        articleRepository.deleteById(id)
    }

    fun updateArticle(article: Article): Article {
        if (!articleRepository.existsById(article.id)) {
            throw EntityNotFoundException("Article Not Found")
        }
        return articleRepository.save(article)
    }

    fun getArticlesByTitle(title: String): MutableList<Article> {
        return articleRepository.findByTitle(title)
    }

}