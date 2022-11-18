package com.atguigu;

import com.atguigu.transaction.BookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-jdbc.xml")
public class TestTx {
    @Autowired
    BookController bookController;

    @Test
    public void testBookBuy(){
        bookController.buyBook(1, 1);
    }
}
