package com.example.demo.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void save() {
        Product expected = new Product("test", 100d);
        productRepo.save(expected);

        Product actual = entityManager.find(Product.class, 1l);
        entityManager.flush();

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertTrue(expected.getPrice() == actual.getPrice());
    }

    @Test
    public void update() {
        Product expected = new Product("test", 100d);
        entityManager.persist(expected);

        expected.setPrice(200d);
        productRepo.save(expected);

        Product actual = entityManager.find(Product.class, 1l);
        entityManager.flush();

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertTrue(expected.getPrice() == actual.getPrice());
    }

}
