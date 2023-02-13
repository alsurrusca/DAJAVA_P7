package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeControllerTest {

    @Autowired
    private TradeController tradeController;

    @MockBean
    private TradeService tradeService;

    private final Trade trade = new Trade("account","type");
    private final List<Trade> tradeList = Arrays.asList(trade);
    private final Model model = new ExtendedModelMap();
    private final Principal user = new Principal() {

        @Override
        public String getName() {
            return "testUser";
        }
    };

    @Before
    public void setUp() {
        when(tradeService.findAll()).thenReturn(tradeList);
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));
    }

    @Test
    public void addTradeForm() {
        String view = tradeController.add(trade);
        assertEquals("trade/add", view);
    }

    @Test
    public void validateTrade() {
        BindingResult result = new BeanPropertyBindingResult(trade, "trade");
        String view = tradeController.validate(trade, result, model);
        assertEquals("redirect:/trade/list", view);
        verify(tradeService, times(1)).save(trade);
        assertEquals(tradeList, model.asMap().get("tradeList"));
    }

    @Test
    public void showUpdateForm() {
        String view = tradeController.showUpdateForm(1, model);
        assertEquals("trade/update", view);
        assertEquals(trade, model.asMap().get("trade"));
    }

    @Test
    public void updateTrade() {
        BindingResult result = new BeanPropertyBindingResult(trade, "trade");
        String view = tradeController.updateTrade(1, trade, result, model);
        assertEquals("redirect:/trade/list", view);
        verify(tradeService, times(1)).save(trade);
        assertEquals(tradeList, model.asMap().get("tradeList"));
    }

    @Test
    public void deleteTrade() {
        String view = tradeController.deleteTrade(1, model);
        assertEquals("redirect:/trade/list", view);
        verify(tradeService, times(1)).delete(trade);
    }
}
