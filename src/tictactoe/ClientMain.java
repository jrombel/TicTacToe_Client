package tictactoe;

import java.rmi.Naming;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ClientMain extends Application {

    public static Text text = new Text();
    public static int id = -1;
    public static int status = -1;
    public static int statusTmp = -1;
    //0 - wait on second player
    //1 - my turn
    //2 - opponent turn
    //3 - my win
    //4 - opponent win
    //5 - dead-heat

    public static ServerInterface myRemoteObject;

    public static Button[] btn = new Button[9];

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.add(text, 0, 0, 3, 1);
        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setMargin(text, new Insets(5, 0, 0, 0));

        for (int i = 0; i < 9; i++) {
            btn[i] = new Button();
            btn[i].setMinWidth(100);
            btn[i].setMinHeight(100);

            GridPane.setMargin(btn[i], new Insets(5));
            if (i < 3) {
                gridPane.add(btn[i], i % 3, 1);
            } else if (i < 6) {
                gridPane.add(btn[i], i % 3, 2);
            } else {
                gridPane.add(btn[i], i % 3, 3);
            }

            int tmp = i;
            btn[i].setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Boolean result = false;
                        if (status == 1) {

                            result = myRemoteObject.selectField(id, tmp);
                        }
                        if (result == true) {
                            drawBoard(myRemoteObject.getBoard());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            );
        }
        Scene scene = new Scene(gridPane, 330, 350);
        primaryStage.setTitle("TicTacToe!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void drawBoard(Board board) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    if (board.fields[i] == 0) {
                        btn[i].setText(" ");
                    } else if (board.fields[i] == 1) {
                        btn[i].setText("X");
                    } else if (board.fields[i] == 2) {
                        btn[i].setText("O");
                    }
                }
            }
        });

    }

    public static Runnable checkStatus = new Runnable() {
        public void run() {
            try {
                status = myRemoteObject.checkPlayerStatus(id);
                if (status != statusTmp) {
                    if (status == 0) {
                        text.setText("Waiting for the second player");
                    } else if (status == 1) {
                        text.setText("You turn");
                        drawBoard(myRemoteObject.getBoard());
                    } else if (status == 2) {
                        text.setText("Turn of the opponent");
                    } else if (status == 3) {
                        text.setText("You won :)");
                    } else if (status == 4) {
                        text.setText("You lost :(");
                    } else if (status == 5) {
                        text.setText("Dead-heat!");
                        drawBoard(myRemoteObject.getBoard());
                    }

                    statusTmp = status;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new SecurityManager());

        try {

            //ServerInterface myRemoteObject = (ServerInterface) Naming.lookup("//192.168.43.233/ABC");
            myRemoteObject = (ServerInterface) Naming.lookup("//localhost/ABC");

            id = myRemoteObject.connect();

            //text.setText("Received ID: " + Integer.toString(id));
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(checkStatus, 0, 500, TimeUnit.MILLISECONDS);

            launch(args);

            executor.shutdown();
            myRemoteObject.disconnect(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}