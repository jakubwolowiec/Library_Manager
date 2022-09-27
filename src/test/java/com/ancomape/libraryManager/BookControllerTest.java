package com.ancomape.libraryManager;

import com.ancomape.libraryManager.model.Book;
import com.ancomape.libraryManager.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class BookControllerTest {


    private final MockMvc mockMvc;
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BookControllerTest(MockMvc mockMvc, BookRepository bookRepository, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }

    @Test
     void shouldAddBookToRepository() throws Exception {
    // given
        Book book = new Book("Book1", "Author", 360, 2002, "", false, 1);
        String requestJSON = objectMapper.writeValueAsString(book);
        mockMvc.perform(post("/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJSON))
                .andExpect(status().isCreated());

    // when
        List<Book> books = bookRepository.findAll();

    //then
    assertThat(books.size()).isEqualTo(1);
    }

    @Test
     void shouldDeleteBookById() throws Exception {
    // given
    Book book = new Book("Book1", "Author", 360, 2002, "", false, 1);
    bookRepository.save(book);
    Long bookId = book.getId();

    // when
    mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete/{id}", bookId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).
            andExpect(status().isOk());

    //then
    assertThat(bookRepository.findAll().size()).isEqualTo(0);
    }

    @Test
     void shouldGetAllBooks() throws Exception {
    // given
    Book book1 = new Book("Book1", "Author1", 360, 2002, "", false, 1);
    Book book2 = new Book("Book2", "Author2", 360, 2002, "", false, 1);
    Book book3 = new Book("Book3", "Author3", 360, 2002, "", false, 1);
    bookRepository.save(book1);
    bookRepository.save(book2);
    bookRepository.save(book3);

    // when
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/book/all"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Book> books = objectMapper.readValue(contentAsString, new TypeReference<>(){});

    //then
        assertThat(books.size()).isEqualTo(bookRepository.findAll().size());
    }

    @Test
     void shouldGetBookById() throws Exception {
    // given
        Book book = new Book("Book1", "Author", 360, 2002, "", false, 1);
        bookRepository.save(book);
        Long bookId = book.getId();

    // when
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/book/find/{id}", bookId))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Book returnedBook = objectMapper.readValue(contentAsString, new TypeReference<>(){
        });

     //then
        assertThat(book.getTitle()).isEqualTo(returnedBook.getTitle());
    }
}
