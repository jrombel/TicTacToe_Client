package tictactoe;

import java.rmi.Naming;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class TicTacToe_Client extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();

        Text text = new Text();
        //text.setText("Test");

        gridPane.add(text, 0, 0, 3, 1);
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setMargin(text, new Insets(5, 0, 0, 0));

        Button[] btn = new Button[9];
        for (int i = 0; i < 9; i++) {
            btn[i] = new Button();
            //btn[i].setText(Integer.toString(i));
            btn[i].setMaxWidth(100);
            btn[i].setMinWidth(100);
            btn[i].setMaxHeight(100);
            btn[i].setMinHeight(100);

            GridPane.setMargin(btn[i], new Insets(5));
            if (i < 3) {
                gridPane.add(btn[i], i % 3, 1);
            } else if (i < 6) {
                gridPane.add(btn[i], i % 3, 2);
            } else {
                gridPane.add(btn[i], i % 3, 3);
            }
        }
        btn[0].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[0].getText() == "X") {
                    btn[0].setText("0");
                } else {
                    btn[0].setText("X");
                }
                System.out.println("Field " + 0);
            }
        });
        btn[1].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[1].getText() == "X") {
                    btn[1].setText("0");
                } else {
                    btn[1].setText("X");
                }
                System.out.println("Field " + 1);
            }
        });
        btn[2].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[2].getText() == "X") {
                    btn[2].setText("0");
                } else {
                    btn[2].setText("X");
                }
                System.out.println("Field " + 2);
            }
        });
        btn[3].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[3].getText() == "X") {
                    btn[3].setText("0");
                } else {
                    btn[3].setText("X");
                }
                System.out.println("Field " + 3);
            }
        });
        btn[4].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[4].getText() == "X") {
                    btn[4].setText("0");
                } else {
                    btn[4].setText("X");
                }
                System.out.println("Field " + 4);
            }
        });
        btn[5].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[5].getText() == "X") {
                    btn[5].setText("0");
                } else {
                    btn[5].setText("X");
                }
                System.out.println("Field " + 5);
            }
        });
        btn[6].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[6].getText() == "X") {
                    btn[6].setText("0");
                } else {
                    btn[6].setText("X");
                }
                System.out.println("Field " + 6);
            }
        });
        btn[7].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[7].getText() == "X") {
                    btn[7].setText("0");
                } else {
                    btn[7].setText("X");
                }
                System.out.println("Field " + 7);
            }
        });
        btn[8].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (btn[8].getText() == "X") {
                    btn[8].setText("0");
                } else {
                    btn[8].setText("X");
                }
                System.out.println("Field " + 8);
            }
        });

        Scene scene = new Scene(gridPane, 330, 350);

        primaryStage.setTitle("TicTacToe!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {

        System.out.println("Test");

        //connection to the server and obtaining id
        System.setProperty("java.security.policy", "security.policy");

        System.setSecurityManager(new SecurityManager());

        try {

            //ServerInterface myRemoteObject = (ServerInterface) Naming.lookup("//192.168.43.233/ABC");
            ServerInterface myRemoteObject = (ServerInterface) Naming.lookup("//localhost/ABC");

            String result = myRemoteObject.test("First test");

            System.out.println("The answer received from the server: " + result);

        } catch (Exception e) {

            e.printStackTrace();

        }

        launch(args);

    }
}
