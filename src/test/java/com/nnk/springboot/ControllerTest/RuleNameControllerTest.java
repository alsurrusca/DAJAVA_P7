package com.nnk.springboot.ControllerTest;


import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameControllerTest {

    @Autowired
    private RuleNameController ruleNameController;

    @MockBean
    private RuleNameService ruleNameService;

    private final RuleName ruleName = new RuleName(1, "Test Rule","type");
    private final List<RuleName> ruleNameList = Arrays.asList(ruleName);
    private final Model model = new ExtendedModelMap();
    private final Principal user = new Principal() {

        @Override
        public String getName() {
            return "testUser";
        }
    };

    @Before
    public void setUp() {
        when(ruleNameService.findAll()).thenReturn(ruleNameList);
        when(ruleNameService.findById(1)).thenReturn(Optional.of(ruleName));
    }

    @Test
    public void addRuleForm() {
        String view = ruleNameController.addRuleForm(ruleName);
        assertEquals("ruleName/add", view);
    }

    @Test
    public void validate() {
        BindingResult result = new BeanPropertyBindingResult(ruleName, "ruleName");
        String view = ruleNameController.validate(ruleName, result, model);
        assertEquals("redirect:/ruleName/list", view);
        verify(ruleNameService, times(1)).save(ruleName);
        assertEquals(ruleNameList, model.asMap().get("ruleNameList"));
    }

    @Test
    public void showUpdateForm() {
        String view = ruleNameController.showUpdateForm(1, model);
        assertEquals("ruleName/update", view);
        assertEquals(ruleName, model.asMap().get("ruleName"));
    }

    @Test
    public void updateRuleName() {
        BindingResult result = new BeanPropertyBindingResult(ruleName, "ruleName");
        String view = ruleNameController.updateRuleName(1, ruleName, result, model);
        assertEquals("redirect:/ruleName/list", view);
        verify(ruleNameService, times(1)).save(ruleName);
        assertEquals(ruleNameList, model.asMap().get("ruleNameList"));
    }

    @Test
    public void deleteRuleName() {
        String view = ruleNameController.deleteRuleName(1, model);
        assertEquals("redirect:/ruleName/list", view);
        verify(ruleNameService, times(1)).delete(ruleName);
    }
}
