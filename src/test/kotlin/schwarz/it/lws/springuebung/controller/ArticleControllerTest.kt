package schwarz.it.lws.springuebung.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FunSpec
import io.mockk.*
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import schwarz.it.lws.springuebung.model.Article
import schwarz.it.lws.springuebung.service.ArticleService


class ArticleControllerTest : FunSpec({

    val articleService = mockk<ArticleService>(relaxed = true)
    val objectMapper = ObjectMapper()

    val mockMvc = MockMvcBuilders.standaloneSetup(ArticleController(articleService)).build()

    beforeTest {
        clearAllMocks()
    }

    context("GET /api/articles") {
        test("Should get all articles") {
            every { articleService.getAllArticle() } returns listOf(
                Article(1, "A1", 1.0), Article(2, "A2", 2.0), Article(3, "A3", 3.0)
            ).toMutableList()
            mockMvc.get("/api/articles").andExpect {
                status { isOk() }
                jsonPath("$.size()") { value(3) }
            }
        }

        test("Should get articles by id") {
            val id: Long = 1

            every { articleService.getArticleById(id) } returns Article(id, "A1", 1.0)

            mockMvc.get("/api/articles/1")
                .andExpect {
                    status { isOk() }
                    jsonPath("$.id") { value(id) }
                }
        }

        test("Should get articles by title") {

            every { articleService.getArticlesByTitle("new") } returns mutableListOf(
                Article(1, "A1", 1.0),
                Article(2, "A2", 2.0),
                Article(3, "A3", 3.0)
            )

            mockMvc.get("/api/articles/title/new")
                .andExpect {
                    status { isOk() }
                    jsonPath("$.size()") {
                        value(3)
                    }
                    jsonPath("$[1].title") { value("A2") }
                }
        }

        context("POST /api/articles") {
            test("Should post article") {
                val article = Article(1, "A1", 1.0)

                mockMvc.post("/api/articles") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(article)
                }
                    .andExpect {
                        status { isOk() }
                    }

            }
        }
        context("Delete /api/articles") {
            test("should delete article") {

                val article = Article(1, "A1", 1.0)
                every { articleService.deleteArticleById(article.id) } just runs
                mockMvc.delete("/api/articles/${article.id}") //http://localhost:8080/api/article/1
                    .andExpect {
                        status { isOk() }
                    }

                verify {
                    articleService.deleteArticleById(article.id)
                }
            }
        }

        context("Patch /api/articles") {
            test("Should patch articles") {
                val article = Article(1, "A1", 1.0)
                every { articleService.updateArticle(article) } answers {
                    article.copy(title = "B1")
                }
                mockMvc.patch("/api/articles") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(article)
                }
                    .andExpect {
                        status { isOk() }
                        jsonPath("$.title") { value("B1") }
                    }
            }
        }
    }

})
