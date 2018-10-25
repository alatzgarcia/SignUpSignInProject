/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signupsigninuidesktop.logic;

/**
 *
 * @author Alatz
 */
public class ILogicImplementationFactory {
    
    public static ILogic getLogic(){
        return new ILogicImplementation();
        //return new ILogicTestDataImplementation(); //Imp class with test data
    }
}
