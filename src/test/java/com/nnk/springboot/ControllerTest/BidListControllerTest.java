package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BidListControllerTest {


    @Autowired
    private BidListController bidListController;

    @MockBean
    private BidListService bidListService;

    private final BidList bid = new BidList();
    private final List<BidList> bidList = Arrays.asList(bid);
    private final Model model = new ExtendedModelMap();
    private final Principal user = new Principal() {

        @Override
        public String getName() {
            return "testUser";
        }
    };

    @Before
    public void setUp() {
        when(bidListService.findAll()).thenReturn(bidList);
        when(bidListService.getById(1)).thenReturn(Optional.of(bid));
    }

    @Test
    public void addRatingForm() {
        String view = bidListController.addBidForm(bid);
        assertEquals("bidList/add", view);
    }

    @Test
    public void validateRating() {
        BindingResult result = new BeanPropertyBindingResult(bid, "bidList");
        String view = bidListController.validate(bid, result, model);
        assertEquals("redirect:/bidList/list", view);
        verify(bidListService, times(1)).save(bid);
        assertEquals(bidList, model.asMap().get("bidlists"));
    }

    @Test
    public void showUpdateForm() {
        String view = bidListController.showUpdateForm(1, model);
        assertEquals("bidList/update", view);
        assertEquals(bid, model.asMap().get("bidList"));
    }

    @Test
    public void updateRating() {
        BindingResult result = new BeanPropertyBindingResult(bid, "bidList");
        String view = bidListController.updateBid(1, bid, result, model);
        assertEquals("redirect:/bidList/list", view);
        verify(bidListService, times(1)).save(bid);
        assertEquals(bidList, model.asMap().get("bidlists"));
    }

    @Test
    public void deleteRating() {
        String view = bidListController.deleteBid(1, model);
        assertEquals("redirect:/bidList/list", view);
        verify(bidListService, times(1)).delete(bid);
    }


}
