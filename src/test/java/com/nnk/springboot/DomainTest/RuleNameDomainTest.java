package com.nnk.springboot.DomainTest;

import com.nnk.springboot.domain.RuleName;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RuleNameDomainTest {


        @Test
        public void testGettersAndSetters() {
            RuleName ruleName = new RuleName();

            ruleName.setId(1);
            ruleName.setName("name");
            ruleName.setDescription("description");
            ruleName.setJson("json");
            ruleName.setTemplate("template");
            ruleName.setSqlStr("sqlStr");
            ruleName.setSqlPart("sqlPart");

            assertEquals(1, (int) ruleName.getId());
            assertEquals("name", ruleName.getName());
            assertEquals("description", ruleName.getDescription());
            assertEquals("json", ruleName.getJson());
            assertEquals("template", ruleName.getTemplate());
            assertEquals("sqlStr", ruleName.getSqlStr());
            assertEquals("sqlPart", ruleName.getSqlPart());
        }

        @Test
        public void testConstructor() {
            RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");

            assertEquals("name", ruleName.getName());
            assertEquals("description", ruleName.getDescription());
            assertEquals("json", ruleName.getJson());
            assertEquals("template", ruleName.getTemplate());
            assertEquals("sqlStr", ruleName.getSqlStr());
            assertEquals("sqlPart", ruleName.getSqlPart());
        }
    }


