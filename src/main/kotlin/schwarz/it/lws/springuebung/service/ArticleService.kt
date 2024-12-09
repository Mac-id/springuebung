package schwarz.it.lws.springuebung.service

import com.fasterxml.jackson.databind.util.ExceptionUtil
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.repository.ArticleRepository

@Service
class ArticleService (private val articleRepository: ArticleRepository) {
    fun getAllArticle() = articleRepository.findAll()
    fun addArticle(article: Article) = articleRepository.save(article)
    fun deleteArticleById(id: Long) = articleRepository.deleteById(id)
    fun updateArticle(article: Article): Article {
        val existingArticle = articleRepository.findById(article.id)
            .orElseThrow { RuntimeException("Article not found") }
        return articleRepository.save(article)
    }

}