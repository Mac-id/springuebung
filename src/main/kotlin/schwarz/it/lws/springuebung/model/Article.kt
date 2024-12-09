package schwarz.it.lws.springuebung.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    val title: String,
    val price: Double
)

