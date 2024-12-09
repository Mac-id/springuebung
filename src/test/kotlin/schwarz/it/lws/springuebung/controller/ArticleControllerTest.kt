package schwarz.it.lws.springuebung.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.MockkBeans
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.service.ArticleService

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [ArticleController::class])
class ArticleControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper
) : FunSpec({

    @MockkBean
    lateinit var articleService: ArticleService
    context("GET /api/articles") {
        test("Should get all articles") {
            every { articleService.getAllArticle() } returns listOf(
                Article(1, "A1", 1.0),
                Article(2, "A2", 2.0),
                Article(3, "A3", 3.0)
            )
            mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk)
        }
    }

})
