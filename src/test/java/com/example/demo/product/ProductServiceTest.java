package com.example.demo.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    private ProductService underTest;

    private ProductModel model;
    private Product product;

    @Before
    public void setup() {
        model = new ProductModel("test", 100d);
        product = new Product(model.getName(), model.getPrice());

        underTest = new ProductService(productRepo);

        when(productRepo.findByName("test"))
                .thenReturn(Optional.of(product));
        when(productRepo.save(product)).thenReturn(product);
    }

    @Test
    public void update() {
        underTest.update(model);

        verify(productRepo, times(1)).save(product);
    }

    // TODO more tests should be added.

}
