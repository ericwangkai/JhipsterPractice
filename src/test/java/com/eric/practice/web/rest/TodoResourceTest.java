package com.eric.practice.web.rest;

import com.eric.practice.Application;
import com.eric.practice.domain.Todo;
import com.eric.practice.repository.TodoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TodoResource REST controller.
 *
 * @see TodoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TodoResourceTest {

    private static final String DEFAULT_TITLE = "SAMPLE_TEXT";
    private static final String UPDATED_TITLE = "UPDATED_TEXT";
    private static final String DEFAULT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_STATUS = "UPDATED_TEXT";

    @Inject
    private TodoRepository todoRepository;

    private MockMvc restTodoMockMvc;

    private Todo todo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TodoResource todoResource = new TodoResource();
        ReflectionTestUtils.setField(todoResource, "todoRepository", todoRepository);
        this.restTodoMockMvc = MockMvcBuilders.standaloneSetup(todoResource).build();
    }

    @Before
    public void initTest() {
        todo = new Todo();
        todo.setTitle(DEFAULT_TITLE);
        todo.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createTodo() throws Exception {
        // Validate the database is empty
        assertThat(todoRepository.findAll()).hasSize(0);

        // Create the Todo
        restTodoMockMvc.perform(post("/api/todos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(todo)))
                .andExpect(status().isCreated());

        // Validate the Todo in the database
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos).hasSize(1);
        Todo testTodo = todos.iterator().next();
        assertThat(testTodo.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTodo.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllTodos() throws Exception {
        // Initialize the database
        todoRepository.saveAndFlush(todo);

        // Get all the todos
        restTodoMockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(todo.getId().intValue()))
                .andExpect(jsonPath("$.[0].title").value(DEFAULT_TITLE.toString()))
                .andExpect(jsonPath("$.[0].status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getTodo() throws Exception {
        // Initialize the database
        todoRepository.saveAndFlush(todo);

        // Get the todo
        restTodoMockMvc.perform(get("/api/todos/{id}", todo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(todo.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTodo() throws Exception {
        // Get the todo
        restTodoMockMvc.perform(get("/api/todos/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTodo() throws Exception {
        // Initialize the database
        todoRepository.saveAndFlush(todo);

        // Update the todo
        todo.setTitle(UPDATED_TITLE);
        todo.setStatus(UPDATED_STATUS);
        restTodoMockMvc.perform(put("/api/todos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(todo)))
                .andExpect(status().isOk());

        // Validate the Todo in the database
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos).hasSize(1);
        Todo testTodo = todos.iterator().next();
        assertThat(testTodo.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTodo.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteTodo() throws Exception {
        // Initialize the database
        todoRepository.saveAndFlush(todo);

        // Get the todo
        restTodoMockMvc.perform(delete("/api/todos/{id}", todo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos).hasSize(0);
    }
}
