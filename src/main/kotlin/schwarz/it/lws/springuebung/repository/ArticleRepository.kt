package schwarz.it.lws.springuebung.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import schwarz.it.lws.springuebung.model.Article

@Repository
interface ArticleRepository : JpaRepository <Article, Long>{
}
