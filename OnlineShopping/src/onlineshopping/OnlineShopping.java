/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlineshopping;

/**
 *
 * @author sct_n
 */
public class OnlineShopping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Call View, Model, and Controller class
         */
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        model.addObserver(view);
    }
    
}
