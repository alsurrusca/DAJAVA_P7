package com.nnk.springboot.DomainTest;
import com.nnk.springboot.domain.BidList;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class BidDomainTest {

    @Test
    public void testBidListProperties() {
        Timestamp bidListDate = new Timestamp(System.currentTimeMillis());
        BidList bidList = new BidList("account", "type", 10.0);
        bidList.setAsk(20.0);
        bidList.setAskQuantity(30.0);
        bidList.setBid(40.0);
        bidList.setBidListDate(bidListDate);
        bidList.setBenchmark("benchmark");
        bidList.setBook("book");
        bidList.setCommentary("commentary");
        bidList.setCreationDate(bidListDate);
        bidList.setCreationName("creationName");
        bidList.setDealName("dealName");
        bidList.setDealType("dealType");
        bidList.setId(1);
        bidList.setRevisionDate(bidListDate);
        bidList.setRevisionName("revisionName");
        bidList.setSecurity("security");
        bidList.setSide("side");
        bidList.setSourceListId("sourceListId");
        bidList.setStatus("status");
        bidList.setTrader("trader");

        assertEquals(1, (int) bidList.getId());
        assertEquals("account", bidList.getAccount());
        assertEquals("type", bidList.getType());
        assertEquals(10.0, bidList.getBidQuantity(), 0.0);
        assertEquals(30.0, bidList.getAskQuantity(), 0.0);
        assertEquals(40.0, bidList.getBid(), 0.0);
        assertEquals(20.0, bidList.getAsk(), 0.0);
        assertEquals("benchmark", bidList.getBenchmark());
        assertEquals(bidListDate, bidList.getBidListDate());
        assertEquals("commentary", bidList.getCommentary());
        assertEquals("security", bidList.getSecurity());
        assertEquals("status", bidList.getStatus());
        assertEquals("trader", bidList.getTrader());
        assertEquals("book", bidList.getBook());
        assertEquals("creationName", bidList.getCreationName());
        assertEquals(bidListDate, bidList.getCreationDate());
        assertEquals("revisionName", bidList.getRevisionName());
        assertEquals(bidListDate, bidList.getRevisionDate());
        assertEquals("dealName", bidList.getDealName());
        assertEquals("dealType", bidList.getDealType());
        assertEquals("sourceListId", bidList.getSourceListId());
        assertEquals("side", bidList.getSide());
    }
}
