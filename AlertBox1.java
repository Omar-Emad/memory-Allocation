package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertBox1 {
    public static void display(Label l)

    {
        Stage window = new Stage();
        Scene scene;
        VBox alert = new VBox();
        window.setTitle("Alert");
        alert.setPadding(new Insets(30,30,30,30));
        l.setStyle(" -fx-font-size : 17; -fx-text-fill : #c208db");
        Button Ok=new Button();
        Ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                window.close();
            }
        });
        Ok.setText("OK");
        Ok.setStyle(" -fx-font-size : 17; -fx-text-fill : #c208db");
        alert.getChildren().addAll(l,Ok);
        scene = new Scene(alert);
        window.setScene(scene);
        window.show();



    }


}
