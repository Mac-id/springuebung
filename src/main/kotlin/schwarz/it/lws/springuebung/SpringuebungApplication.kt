package schwarz.it.lws.springuebung

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.repository.ArticleRepository

@SpringBootApplication
class SpringuebungApplication(private val articleRepository: ArticleRepository) {
    @Bean
    fun getCommandLineRunner(articleRepository: ArticleRepository) = CommandLineRunner { args ->
        val article1 = Article(0, "apfel", 1.99)
        val article2 = Article(0, "banane", 6.99)
        articleRepository.save(article1)
        articleRepository.save(article2)
    }


}

fun main(args: Array<String>) {
    runApplication<SpringuebungApplication>(*args)


}

