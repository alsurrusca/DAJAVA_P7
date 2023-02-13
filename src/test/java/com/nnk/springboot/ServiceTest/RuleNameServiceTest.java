package com.nnk.springboot.ServiceTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameServiceTest {



    @Autowired
    private RuleNameService ruleNameService;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Before
    public void setUp() {
        ruleNameRepository.deleteAll();
    }

    @After
    public void tearDown() {
        ruleNameRepository.deleteAll();
    }

    @Test
    public void findAllTest(){

        List<RuleName> ruleNames = ruleNameService.findAll();
        assertNotNull(ruleNames);

    }

    @Test
    public void saveTest(){
        RuleName ruleName = new RuleName();
        ruleName.setName("test ruleName list");
        ruleName.setDescription("description");
        ruleName = ruleNameService.save(ruleName);
        assertNotNull(ruleName.getId());
    }


    @Test
    public void getByIdTest(){

        RuleName ruleName = new RuleName();
        ruleName.setName("test ruleName list");
        ruleName.setDescription("description");
        ruleName = ruleNameService.save(ruleName);
        // When
        Optional<RuleName> ruleNameServiceById = ruleNameService.findById(ruleName.getId());

        // Then
        assertTrue(ruleNameServiceById.isPresent());
        assertEquals(ruleName.getName(), ruleNameServiceById.get().getName());

    }

    @Test
    public void testDelete() {
        // Given
        RuleName ruleName = new RuleName();
        ruleName.setName("test ruleName list");
        ruleName.setDescription("description");
        ruleName = ruleNameService.save(ruleName);
        // When
        ruleNameService.delete(ruleName);

        // Then
        Optional<RuleName> ruleNameServiceById = ruleNameService.findById(ruleName.getId());
        assertFalse(ruleNameServiceById.isPresent());
    }
}


