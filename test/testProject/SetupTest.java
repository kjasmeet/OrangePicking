/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testProject;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author JasmeetKaur
 */
public class SetupTest {

    public static void main(String args[]) {
        Result result = JUnitCore.runClasses(testProject.TestProject.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
