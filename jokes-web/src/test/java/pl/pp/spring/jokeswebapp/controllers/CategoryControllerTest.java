package pl.pp.spring.jokeswebapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    private Category category1 = new Category("kategoria 1");


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void showCategoryListForNotExistCategories() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories/list"))
                .andExpect(model().attribute("categories", hasSize(0)));

        verify(categoryService).findAll();
    }

    @Test
    void showCategoryListForExistCategories() throws Exception {
        List<Category> listCategories = new ArrayList<>();
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        listCategories.add(category1);
        listCategories.add(category2);

        when(categoryService.findAll()).thenReturn(listCategories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories/list"))
                .andExpect(model().attribute("categories", hasSize(2)));

        verify(categoryService).findAll();
    }

    @Test
    void testShowCategoryForm() throws Exception {
        mockMvc.perform(get("/categories/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("categories/save"));
    }

    @Test
    void processCategoryForm() throws Exception {
        mockMvc.perform(post("/categories/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

        verify(categoryService).save(any(Category.class));
    }

    @Test
    void showEditCategoryForm() throws Exception {
        when(categoryService.findById(anyLong())).thenReturn(category1);

        //przykładowa wartość 2 typu Long
        mockMvc.perform(get("/categories/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category")) //czy istnieje
                .andExpect(view().name("categories/save"));

        verify(categoryService).findById(2L);
    }

    @Test
    void deleteExistCategory() throws Exception {
        mockMvc.perform(get("/categories/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

        verify(categoryService).deleteById(1L);
    }

    @Test
    void deleteNotExistCategory() throws Exception {

        doThrow(NotFoundException.class).when(categoryService).deleteById(anyLong());
        //when(categoryService.deleteById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/categories/1/delete"))
                .andExpect(status().isNotFound());

        verify(categoryService).deleteById(1L);
    }

}