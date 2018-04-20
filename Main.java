package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Vector;

public class Main extends Application implements EventHandler<ActionEvent>  {


    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void sort(Vector<hole> holes,String method) {   // "FF" for first fit & "BF" for best fit
       hole temp;
       boolean swap;
        if (method == "FF") {
            for(int i=0;i<holes.size()-1;i++)
            {
                swap=false;
                for(int j=0;j<holes.size()-i-1;j++)
                {
                    if(holes.get(j).getStartAddress()>holes.get(j+1).getStartAddress())
                    {
                        temp=holes.get(j);
                        holes.set(j,holes.get(j+1));
                        holes.set(j+1,temp);
                        swap=true;
                    }
                }
                if(!swap) break;
            }
            //------------------------------------------
        } else if (method == "BF") {
            for(int i=0;i<holes.size()-1;i++)
            {
                swap=false;
                for(int j=0;j<holes.size()-i-1;j++)
                {
                    if(holes.get(j).getSize()>holes.get(j+1).getSize())
                    {
                        temp=holes.get(j);
                        holes.set(j,holes.get(j+1));
                        holes.set(j+1,temp);
                        swap=true;
                    }
                }
                if(!swap) break;
            }
        }
    }
    

    @Override
    public void handle(ActionEvent event) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}

