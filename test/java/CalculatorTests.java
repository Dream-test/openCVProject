import org.junit.jupiter.api.Test;
import org.opencv.core.Core;

import java.awt.*;
import java.awt.event.InputEvent;

public class CalculatorTests {
    private final String templ_clean = "src/main/resources/templ_clean.png";
    private final String templ_1 = "src/main/resources/templ_1.png";
    private final String templ_2 = "src/main/resources/templ_2.png";
    private final String templ_3 = "src/main/resources/templ_3.png";
    private final String templ_plus = "src/main/resources/templ_plus.png";
    private final String templ_result = "src/main/resources/templ_result.png";
    private final String templ_result_4 = "src/main/resources/templ_result_4.png";

    private final Rect searchArea = new Rect(0, 0, 705, 540);
    private double precision;

    @Test
    public void sumTwoNumbers() throws Exception {
        String forSumTwoNumbersScreen = "sumtwonumbers";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Rect buttonClean = new ButtonByTemplate(searchArea, templ_clean, forSumTwoNumbersScreen).getButton();
        click(buttonClean.Center());
        Rect buttonOne = new ButtonByTemplate(searchArea, templ_1, forSumTwoNumbersScreen).getButton();
        click(buttonOne.Center());
        Rect buttonPlus = new ButtonByTemplate(searchArea, templ_plus, forSumTwoNumbersScreen).getButton();
        click(buttonPlus.Center());
        Rect buttonThree = new ButtonByTemplate(searchArea, templ_3, forSumTwoNumbersScreen).getButton();
        click(buttonThree.Center());
        Rect buttonResult = new ButtonByTemplate(searchArea, templ_result, forSumTwoNumbersScreen).getButton();
        click(buttonResult.Center());
        Thread.sleep(500);
        Rect resultField = new ButtonByTemplate(searchArea, templ_result_4, forSumTwoNumbersScreen).getButton();
    }

    @Test
    public void sumTwoNumbersWithNegativeAssert() throws Exception {
        String forSumTwoNumbersScreen = "sumnegative";
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Rect buttonClean = new ButtonByTemplate(searchArea, templ_clean, forSumTwoNumbersScreen).getButton();
        click(buttonClean.Center());
        Rect buttonOne = new ButtonByTemplate(searchArea, templ_1, forSumTwoNumbersScreen).getButton();
        click(buttonOne.Center());
        Rect buttonPlus = new ButtonByTemplate(searchArea, templ_plus, forSumTwoNumbersScreen).getButton();
        click(buttonPlus.Center());
        Rect buttonThree = new ButtonByTemplate(searchArea, templ_2, forSumTwoNumbersScreen).getButton();
        click(buttonThree.Center());
        Rect buttonResult = new ButtonByTemplate(searchArea, templ_result, forSumTwoNumbersScreen).getButton();
        click(buttonResult.Center());
        Thread.sleep(500);
        try {
            Rect resultField = new ButtonByTemplate(searchArea, templ_result_4, forSumTwoNumbersScreen).getButton();
        } catch (Exception e) {
            System.out.println("Result is not as expected, result " + e.getMessage());
        }
    }

    private void click(Point p) throws AWTException {
        Robot robot = new Robot();
        move(p);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void move(Point p) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(p.x, p.y);
    }
}
