/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxproject;

import static java.lang.Character.isDigit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class categoryPageController implements Initializable {

    @FXML
    TextField dashboard;
    @FXML
    Button clearBtn;
    @FXML
    Button NormalButton;
    @FXML
    Button Btn0;
    @FXML
    Button Btn1;
    @FXML
    Button Btn2;
    @FXML
    Button Btn3;
    @FXML
    Button Btn4;
    @FXML
    Button Btn5;
    @FXML
    Button Btn6;
    @FXML
    Button Btn7;
    @FXML
    Button Btn8;
    @FXML
    Button Btn9;
    @FXML
    Button div;
    @FXML
    Button mul;
    @FXML
    Button plus;
    @FXML
    Button sub;
    @FXML
    Button equl;
    @FXML
    Button dot;

    static int indexBefore;
    static int indexAfter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    
//    public void ClearFun(Event e) {
//        dashboard.setText("");
//    }
//
//    public void getIndexBefore(int index) {
//        indexBefore = index;
//    }
//
//    public void getIndexAfter(int index) {
//        indexAfter = index;
//    }
//
//    public String getTheNumberBefore(int baseNode, String Text) {
//        int iteration = baseNode - 1;
//        StringBuilder beforeNum = new StringBuilder("");
//        while (true) {
//            if (iteration>=0&&isDigit(Text.charAt(iteration))) {
//                beforeNum.append(Text.charAt(iteration));
//                iteration -= 1;
//            } else {
//                getIndexBefore(iteration + 1);
//                return beforeNum.reverse().toString();
//            }
//        }
//
//    }
//
//    public String getTheNumberAfter(int baseNode, String Text) {
//        int iteration = baseNode + 1;
//        StringBuilder beforeNum = new StringBuilder("");
//        while (true) {
//            if (iteration<Text.length()&&isDigit(Text.charAt(iteration))) {
//                beforeNum.append(Text.charAt(iteration));
//                iteration += 1;
//            } else {
//                getIndexAfter(iteration);
//                return beforeNum.toString();
//            }
//        }
//
//    }
//
//    public void writeNum(Event e) {
//        if (e.getSource() == Btn0) {
//            dashboard.setText(dashboard.getText() + 0);
//        } else if (e.getSource() == Btn1) {
//            dashboard.setText(dashboard.getText() + 1);
//        } else if (e.getSource() == Btn2) {
//            dashboard.setText(dashboard.getText() + 2);
//        } else if (e.getSource() == Btn3) {
//            dashboard.setText(dashboard.getText() + 3);
//        } else if (e.getSource() == Btn4) {
//            dashboard.setText(dashboard.getText() + 4);
//        } else if (e.getSource() == Btn5) {
//            dashboard.setText(dashboard.getText() + 5);
//        } else if (e.getSource() == Btn6) {
//            dashboard.setText(dashboard.getText() + 6);
//        } else if (e.getSource() == Btn7) {
//            dashboard.setText(dashboard.getText() + 7);
//        } else if (e.getSource() == Btn8) {
//            dashboard.setText(dashboard.getText() + 8);
//        } else if (e.getSource() == Btn9) {
//            dashboard.setText(dashboard.getText() + 9);
//        } else if (e.getSource() == plus) {
//            dashboard.setText(dashboard.getText() + '+');
//        } else if (e.getSource() == sub) {
//            dashboard.setText(dashboard.getText() + '-');
//        } else if (e.getSource() == mul) {
//            dashboard.setText(dashboard.getText() + '*');
//        } else if (e.getSource() == div) {
//            dashboard.setText(dashboard.getText() + '/');
//        } else if (e.getSource() == dot) {
//            dashboard.setText(dashboard.getText() + '.');
//        } else  {
//            String text = dashboard.getText();
//            while (true) {
//                if (text.indexOf('*') >= 0) {
//                    int numberBefore = Integer.parseInt(getTheNumberBefore(text.indexOf('*'), text));
//                    int numberAfter = Integer.parseInt(getTheNumberAfter(text.indexOf('*'), text));
//                    int Total = numberBefore * numberAfter;
//                    text = text.replace(text.substring(indexBefore, indexAfter), String.valueOf(Total));
//                } else if (text.indexOf('/') >= 0) {
//                    int numberBefore = Integer.parseInt(getTheNumberBefore(text.indexOf('/'), text));
//                    int numberAfter = Integer.parseInt(getTheNumberAfter(text.indexOf('/'), text));
//                    int Total = 0;
//                    try {
//                        Total = numberBefore / numberAfter;
//                    } catch (java.lang.ArithmeticException ex) {
//                        System.out.println("Can't divid by zero");
//                    }
//                    text = text.replace(text.substring(indexBefore, indexAfter), String.valueOf(Total));
//                } else if (text.indexOf('+') >= 0) {
//                    int numberBefore = Integer.parseInt(getTheNumberBefore(text.indexOf('+'), text));
//                    int numberAfter = Integer.parseInt(getTheNumberAfter(text.indexOf('+'), text));
//                    int Total = numberBefore + numberAfter;
//                    text = text.replace(text.substring(indexBefore, indexAfter), String.valueOf(Total));
//                } else if (text.indexOf('-') >= 0) {
//                    int numberBefore = Integer.parseInt(getTheNumberBefore(text.indexOf('-'), text));
//                    int numberAfter = Integer.parseInt(getTheNumberAfter(text.indexOf('-'), text));
//                    int Total = numberBefore - numberAfter;
//                    if (Total < 0){
//                        Total*=-1;
//                        text = text.replace(text.substring(indexBefore, indexAfter), "-"+String.valueOf(Total));
//                    }
//                    text = text.replace(text.substring(indexBefore, indexAfter), String.valueOf(Total));
//                } else {
//                    dashboard.setText(text);
//                    text = "";
//                    break;
//                }
//            }
//        }
//    }

}

//5 + 6 * 2
//while (true){
//    if (s.indexOf('*'))
//        getTheNumberBefore();
//        getIndexBefore();
//        getTheNumberAfter();
//        getIndexAfter();
//        s.replace (resultOfCalc, beforeIndex, afterIndex);   
//}
