package com.example.ecommerceweb;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.ecommerceweb.model.Category;
import com.example.ecommerceweb.repository.CategoryRepository;
import com.example.ecommerceweb.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class testCategory {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testAdd(){
//        Category category = new Category();
//        category.setName("Cam");
//        category.setDesc("Nhieu dinh duong");
//        category.setImage("Test");
//        category.setPrice(200);
//        category.setQuantity(5);
//
//        Category saveCat = categoryRepository.save(category);
//
//        Category existUser = entityManager.find(Category.class, saveCat.getId());
//
//        assertThat(existUser.getId()).isEqualTo(saveCat.getId());

    }
}
