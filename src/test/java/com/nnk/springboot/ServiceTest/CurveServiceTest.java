package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
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

public class CurveServiceTest {

    @Autowired
    private CurvePointService curvePointService;

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Before
    public void setUp() {
        curvePointRepository.deleteAll();
    }

    @After
    public void tearDown() {
        curvePointRepository.deleteAll();
    }

    @Test
    public void findAllTest(){

        List<CurvePoint> curvePointList = curvePointService.findAll();
        assertNotNull(curvePointList);

    }

    @Test
    public void saveTest(){
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        curvePoint.setValue(1.1);
        curvePoint = curvePointService.save(curvePoint);
        assertNotNull(curvePoint.getId());
    }


    @Test
    public void getByIdTest(){

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        curvePoint.setValue(1.1);
        curvePoint = curvePointService.save(curvePoint);

        // When
        Optional<CurvePoint> foundBidList = curvePointService.findById(curvePoint.getId());

        // Then
        assertTrue(foundBidList.isPresent());
        assertEquals(curvePoint.getTerm(), foundBidList.get().getTerm());

    }

    @Test
    public void testDelete() {
        // Given
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        curvePoint.setValue(1.1);
        curvePoint = curvePointService.save(curvePoint);


        // When
        curvePointService.delete(curvePoint);

        // Then
        Optional<CurvePoint> deletedBidList = curvePointService.findById(curvePoint.getId());
        assertFalse(deletedBidList.isPresent());
    }
}
