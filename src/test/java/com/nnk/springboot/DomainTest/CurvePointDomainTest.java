package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.CurvePoint;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

public class CurvePointDomainTest {

    @Test
    public void CurvePointTest(){
        Timestamp curvePointDate = new Timestamp(System.currentTimeMillis());
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setValue(10.0);
        curvePoint.setTerm(10.0);
        curvePoint.setAsOfDate(curvePointDate);
        curvePoint.setCurveId(1);
        curvePoint.setCreationDate(curvePointDate);


        assertEquals(java.util.Optional.of(1),java.util.Optional.of(curvePoint.getId()));
        assertEquals(java.util.Optional.of(10.0),java.util.Optional.of(curvePoint.getValue()));
        assertEquals(java.util.Optional.of(10.0),java.util.Optional.of(curvePoint.getTerm()));
        assertEquals(java.util.Optional.of(1),java.util.Optional.of(curvePoint.getCurveId()));
        assertEquals(curvePointDate, curvePoint.getAsOfDate());
        assertEquals(curvePointDate, curvePoint.getCreationDate());


    }
}
