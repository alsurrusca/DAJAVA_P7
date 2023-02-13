package com.nnk.springboot.ServiceTest;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TradeServiceTest {




    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeRepository tradeRepository;

    @Before
    public void setUp() {
        tradeRepository.deleteAll();
    }

    @After
    public void tearDown() {
        tradeRepository.deleteAll();
    }

    @Test
    public void findAllTest(){

        List<Trade> trades = tradeService.findAll();
        assertNotNull(trades);

    }

    @Test
    public void saveTest(){
        Trade trade = new Trade();
        trade.setAccount("test trade list");
        trade.setType("type");
        trade = tradeService.save(trade);
        assertNotNull(trade.getTradeId());
    }


    @Test
    public void getByIdTest(){

        Trade trade = new Trade();
        trade.setAccount("test trade list");
        trade.setType("type");
        trade = tradeService.save(trade);
        // When
        Optional<Trade> tradeServiceById = tradeService.findById(trade.getTradeId());

        // Then
        assertTrue(tradeServiceById.isPresent());
        assertEquals(trade.getAccount(), tradeServiceById.get().getAccount());

    }

    @Test
    public void testDelete() {
        // Given
        Trade trade = new Trade();
        trade.setAccount("test trade list");
        trade.setType("type");
        trade = tradeService.save(trade);

        // When
        tradeService.delete(trade);

        // Then
        Optional<Trade> tradeServiceById = tradeService.findById(trade.getTradeId());
        assertFalse(tradeServiceById.isPresent());
    }
}


