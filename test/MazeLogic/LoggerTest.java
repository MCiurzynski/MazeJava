/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package MazeLogic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author golde
 */
public class LoggerTest {

    Logger instance;

    public LoggerTest() {
        instance = new Logger();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addLog method, of class Logger.
     */
    @Test
    public void testLogger() {
        var adder = new Adder();
        var getter = new Getter();
        adder.start();
        getter.start();
    }

    private class Adder extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
                instance.addLog(String.valueOf(i));
            }
        }
    }

    private class Getter extends Thread {

        public Getter() {
            super();
            setDaemon(true);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            String s;
            while (true) {
                s = instance.getLog();
                System.out.println(s);
            }

        }
    }

}
