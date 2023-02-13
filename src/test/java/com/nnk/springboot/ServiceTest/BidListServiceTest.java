package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
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
public class BidListServiceTest {

    @Autowired
    private BidListService bidListService;

    @Autowired
    private BidListRepository bidListRepository;

    @Before
    public void setUp() {
        bidListRepository.deleteAll();
    }

    @After
    public void tearDown() {
        bidListRepository.deleteAll();
    }

    @Test
    public void findAllTest(){

        List<BidList> bidLists = bidListService.findAll();
        assertNotNull(bidLists);

    }

    @Test
    public void saveBidTest(){
        BidList bidList = new BidList();
        bidList.setAccount("test bid list");
        bidList.setType("type");
        bidList = bidListService.save(bidList);
        assertNotNull(bidList.getId());
    }


  @Test
    public void getByIdTest(){

      BidList bidList = new BidList();
      bidList.setAccount("Test BidList");
      bidList.setType("type");
      bidList = bidListService.save(bidList);

      // When
      Optional<BidList> foundBidList = bidListService.getById(bidList.getId());

      // Then
      assertTrue(foundBidList.isPresent());
      assertEquals(bidList.getAccount(), foundBidList.get().getAccount());

  }

    @Test
    public void testDelete() {
        // Given
        BidList bidList = new BidList();
        bidList.setAccount("Test BidList");
        bidList.setType("type");
        bidList = bidListService.save(bidList);

        // When
        bidListService.delete(bidList);

        // Then
        Optional<BidList> deletedBidList = bidListService.getById(bidList.getId());
        assertFalse(deletedBidList.isPresent());
    }
}
