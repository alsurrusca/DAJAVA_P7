package com.nnk.springboot.ControllerTest;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurveControllerTest {



    @Autowired
    private CurveController curveController;

    @MockBean
    private CurvePointService curvePointService;

    private final CurvePoint curve = new CurvePoint();
    private final List<CurvePoint> curvePointList = Arrays.asList(curve);
    private final Model model = new ExtendedModelMap();
    private final Principal user = new Principal() {

        @Override
        public String getName() {
            return "testUser";
        }
    };

    @Before
    public void setUp() {
        when(curvePointService.findAll()).thenReturn(curvePointList);
        when(curvePointService.findById(1)).thenReturn(Optional.of(curve));
    }

    @Test
    public void addCurveForm() {
        String view = curveController.add(curve);
        assertEquals("curvePoint/add", view);
    }

    @Test
    public void validateCurve() {
        BindingResult result = new BeanPropertyBindingResult(curve, "curve");
        String view = curveController.validate(curve, result, model);
        assertEquals("redirect:/curvePoint/list", view);
        verify(curvePointService, times(1)).save(curve);
        assertEquals(curvePointList, model.asMap().get("curvePointList"));
    }

    @Test
    public void showUpdateForm() {
        String view = curveController.showUpdateForm(1, model);
        assertEquals("curvePoint/update", view);
        assertEquals(curve, model.asMap().get("curvePoint"));
    }

    @Test
    public void updateCurvePoint() {
        BindingResult result = new BeanPropertyBindingResult(curve, "curve");
        String view = curveController.update(1, curve, result, model);
        assertEquals("redirect:/curvePoint/list", view);
        verify(curvePointService, times(1)).save(curve);
        assertEquals(curvePointList, model.asMap().get("curvePointList"));
    }

    @Test
    public void deleteCurvePoint() {
        String view = curveController.delete(1, model);
        assertEquals("redirect:/curvePoint/list", view);
        verify(curvePointService, times(1)).delete(curve);
    }

}
