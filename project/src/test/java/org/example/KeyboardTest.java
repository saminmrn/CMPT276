package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import java.awt.*;
import java.awt.event.KeyEvent;
import junit.framework.TestSuite;

public class KeyboardTest extends TestCase {
    /**
     * Create the test case.
     * @param testName name of the test case
     */

    public KeyboardTest(String testName)
    {
        super(testName);


    }
    /**
     * @test for W key on the keyboard
     */
    public void testKeyPressedUP() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 87, 'W');
        assertEquals(KeyEvent.VK_W, e.getKeyCode() );
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 38, 'U');
        assertEquals(KeyEvent.VK_UP, e2.getKeyCode());

    }
    /**
     * @test for S key on the keyboard
     */
    public void keyPressedDNTest() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 83, 'S');
        assertEquals( KeyEvent.VK_S, e.getKeyCode());
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 40, 'D');
        assertEquals(KeyEvent.VK_DOWN, e2.getKeyCode());
    }
    /**
     * @test for A key on the keyboard
     */
    public void keyPressedLFTest( ) {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 65, 'A');
        assertEquals(KeyEvent.VK_A, e.getKeyCode());
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 37, 'L');
        assertEquals(KeyEvent.VK_LEFT, e2.getKeyCode());
    }
    /**
     * @test for D key on the keyboard
     */
    public void keyPressedRTTest() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 68, 'D');
        assertEquals(KeyEvent.VK_D, e.getKeyCode() );
        KeyEvent e2 = new KeyEvent(a, 1, 20, 1, 39, 'R');
        assertEquals(KeyEvent.VK_RIGHT, e2.getKeyCode());
    }

    /**
     * @test for D key on the keyboard
     */
    public void wrongKeyPressed() {
        Button a = new Button("click");
        KeyEvent e = new KeyEvent(a, 1, 20, 1, 89, 'Q');
       assertNotSame(KeyEvent.VK_RIGHT, e.getKeyCode());
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
            TestSuite suite = new TestSuite(KeyboardTest.class);
            suite.addTest(new KeyboardTest("testKeyPressedUP"));
            suite.addTest(new KeyboardTest("keyPressedDNTest"));
            suite.addTest(new KeyboardTest("keyPressedLFTest"));
            suite.addTest(new KeyboardTest("keyPressedRTTest"));
            suite.addTest(new KeyboardTest("wrongKeyPressed"));
            return suite;

    }
}
