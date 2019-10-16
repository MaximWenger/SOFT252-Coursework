/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings.Logging;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for AccesSLogger Class
 *
 */
public class AccessLoggerTest {

    private AccessLogger acessLogger;

    /**
     *
     */
    public AccessLoggerTest() {

    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    /**
     * Producing new accessLogger object.
     */
    @Before
    public void setUp() {
        this.acessLogger = new AccessLogger();
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }
    /**
     * Confirming accessLogger object is populated.
     * @throws Exception 
     */
    @Test
    public void testCreateAccessLog() throws Exception {
        System.out.println(this.acessLogger);
    }
    /**
     * Confirming testLocalTime is the same as current local time.
     * @throws Exception 
     */
    @Test
    public void testLocalTime() throws Exception {
        LocalTime local = LocalTime.now();
        local.equals(this.acessLogger.getDateTime());
    }

}
