package sample;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Vector;



public class ResultWindow {
    public static void display(Vector<hole> H,Vector<process> P,Stage window,int MemorySize)
    {
        Scene scene;



        GridPane grid=new GridPane();
        int Hsize=H.size();
        int Psize=P.size();
        int Holescounter=0;
        int Processcounter=0;
        int gridcounter=0  ;
        while((Holescounter< Hsize) && (Processcounter<Psize))
        {
            if(H.elementAt(Holescounter).getStartAddress()<=P.elementAt(Processcounter).getStartAddress())
            {

                Label label = new Label();
                Label ad=new Label("Starting Address : "+Integer.toString(H.elementAt(Holescounter).getStartAddress()));
                ad.setStyle("-fx-background-color: #e8e5ea ; -fx-padding: 10px;");
                ad.setMinWidth(300);
                Label Address=new Label("Ending Address : "+Integer.toString(H.elementAt(Holescounter).getEndAddress()));
                label.setStyle("-fx-background-color: #e8e5ea ; -fx-padding: 10px;");
                Address.setStyle("-fx-background-color: #e8e5ea ; -fx-padding: 10px;");
                label.setMinHeight((H.elementAt(Holescounter).getSize()) * (500 / MemorySize));
                Address.setMinWidth(300);
                label.setMinWidth(300);
                VBox ALL=new VBox();
                ALL.getChildren().addAll(ad,label,Address);
                grid.add(ALL, 0, gridcounter);
                Holescounter++;
                gridcounter++;
            }
            else
            {

                Label ad=new Label("Starting Address : "+Integer.toString(P.elementAt(Processcounter).getStartAddress()));
                ad.setStyle("-fx-background-color: #3e4144 ; -fx-padding: 10px; -fx-text-fill: #ace285;");
                ad.setMinWidth(300);
                Label label = new Label(P.elementAt(Processcounter).getName());
                Label Address=new Label("Ending Address : "+Integer.toString(P.elementAt(Processcounter).getEndAddress()));
                label.setStyle("-fx-background-color: #3e4144 ; -fx-padding: 10px; -fx-text-fill: #be7cea;");
                Address.setStyle("-fx-background-color: #3e4144 ; -fx-padding: 10px; -fx-text-fill: #ace285;");
                label.setMinHeight((P.elementAt(Processcounter).getSize()) * (500 / MemorySize));
                Address.setMinWidth(300);
                label.setMinWidth(300);
                VBox ALL=new VBox();
                ALL.getChildren().addAll(ad,label,Address);
                grid.add(ALL, 0, gridcounter);
                Processcounter++;
                gridcounter++;
            }



        }
        if(Holescounter<Hsize)
        {
            while(Holescounter< Hsize)
            {   Label ad=new Label("Starting Address : "+Integer.toString(H.elementAt(Holescounter).getStartAddress()));
                ad.setStyle("-fx-background-color: #e8e5ea ; -fx-padding: 10px;");
                ad.setMinWidth(300);
                Label label = new Label();
                Label Address=new Label("Ending Address : "+Integer.toString(H.elementAt(Holescounter).getEndAddress()));
                label.setStyle("-fx-background-color: #e8e5ea ; -fx-padding: 10px;");
                Address.setStyle("-fx-background-color: #e8e5ea ; -fx-padding: 10px;");
                label.setMinHeight((H.elementAt(Holescounter).getSize()) * (500 / MemorySize));
                Address.setMinWidth(300);
                label.setMinWidth(300);
                VBox ALL=new VBox();
                ALL.getChildren().addAll(ad,label,Address);
                grid.add(ALL, 0, gridcounter);

                Holescounter++;
                gridcounter++;
            }

        }
        if((Processcounter<Psize))
        {
            while(Processcounter<Psize)
            {
                Label ad=new Label("Starting Address : "+Integer.toString(P.elementAt(Processcounter).getStartAddress()));
                ad.setStyle("-fx-background-color: #3e4144 ; -fx-padding: 10px; -fx-text-fill: #ace285;");
                ad.setMinWidth(300);
                Label label = new Label(P.elementAt(Processcounter).getName());
                Label Address=new Label("Ending Address : "+Integer.toString(P.elementAt(Processcounter).getEndAddress()));
                label.setStyle("-fx-background-color: #3e4144 ; -fx-padding: 10px; -fx-text-fill: #be7cea;");
                Address.setStyle("-fx-background-color: #3e4144 ; -fx-padding: 10px; -fx-text-fill: #ace285;");
                label.setMinHeight((P.elementAt(Processcounter).getSize()) * (500 / MemorySize));
                Address.setMinWidth(300);
                label.setMinWidth(300);
                VBox ALL=new VBox();
                ALL.getChildren().addAll(ad,label,Address);
                grid.add(ALL, 0, gridcounter);
                Processcounter++;
                gridcounter++;
            }
        }
        ScrollPane ss=new ScrollPane(grid);
       // ss.setFitToHeight(true);
        scene = new Scene(ss,400,500);
        window.setScene(scene);
        window.show();

    }
    public static void close()
    {

    }
}
