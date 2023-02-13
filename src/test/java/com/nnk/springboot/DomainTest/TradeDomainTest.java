package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.Trade;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;


public class TradeDomainTest {

    @Test
    public void testTradeProperties() {
        Timestamp tradeDate = new Timestamp(System.currentTimeMillis());
        Trade trade = new Trade("account", "type");
        trade.setBuyPrice(20.0);
        trade.setBuyQuantity(30.0);
        trade.setTradeDate(tradeDate);
        trade.setBenchmark("benchmark");
        trade.setBook("book");
        trade.setCreationDate(tradeDate);
        trade.setCreationName("creationName");
        trade.setDealName("dealName");
        trade.setDealType("dealType");
        trade.setTradeId(1);
        trade.setRevisionDate(tradeDate);
        trade.setRevisionName("revisionName");
        trade.setSecurity("security");
        trade.setSide("side");
        trade.setSourceListId("sourceListId");
        trade.setStatus("status");
        trade.setTrader("trader");
        trade.setSellPrice(10.0);
        trade.setSellQuantity(10.0);

        assertEquals(1, (int) trade.getTradeId());
        assertEquals("account", trade.getAccount());
        assertEquals("type", trade.getType());
        assertEquals(30.0, trade.getBuyQuantity(), 0.0);
        assertEquals(20.0, trade.getBuyPrice(), 0.0);
        assertEquals("benchmark", trade.getBenchmark());
        assertEquals(tradeDate, trade.getTradeDate());
        assertEquals("security", trade.getSecurity());
        assertEquals("status", trade.getStatus());
        assertEquals("trader", trade.getTrader());
        assertEquals("book", trade.getBook());
        assertEquals("creationName", trade.getCreationName());
        assertEquals(tradeDate, trade.getCreationDate());
        assertEquals("revisionName", trade.getRevisionName());
        assertEquals(tradeDate, trade.getRevisionDate());
        assertEquals("dealName", trade.getDealName());
        assertEquals("dealType", trade.getDealType());
        assertEquals("sourceListId", trade.getSourceListId());
        assertEquals("side", trade.getSide());
        assertEquals(java.util.Optional.of(10.0), java.util.Optional.of(trade.getSellPrice()));
        assertEquals(java.util.Optional.of(10.0), java.util.Optional.of(trade.getSellQuantity()));

    }

    @Test
    public void tradeConstructorTest(){
        String account = "account";
        String type = "type";

        Trade trade = new Trade(account, type);
        assertEquals(account, trade.getAccount());
        assertEquals(type, trade.getType());
    }
}

