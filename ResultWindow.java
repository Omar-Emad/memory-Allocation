package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import java.util.List;
import java.util.Vector;


public class ResultWindow {
    public static void display(Vector<hole> l,Vector<process> p,Stage window)
    {

        Scene scene;
        TableView Holes = new TableView();
        TableView AllocatedMemory = new TableView();
        Holes.setEditable(true);
        AllocatedMemory.setEditable(true);
        TableColumn HoleStart = new TableColumn("Hole Start");
        TableColumn HoleSize = new TableColumn("Hole Size");
        TableColumn AllocatedBlockStart = new TableColumn("AllocatedBlockStart");
        TableColumn AllocatedBlockSize = new TableColumn("AllocatedBlockSize");
        TableColumn AllocatedBlockProcess = new TableColumn("ProcessAllocatedBy");
        List <hole> ll=l;
        List <process> pp=p;
        final ObservableList<hole> Hole= FXCollections.observableList(ll);
        final ObservableList<process> Process=FXCollections.observableList(pp);
        HoleStart.setCellValueFactory(new PropertyValueFactory<hole,Integer>("startAddress"));
        HoleSize.setCellValueFactory(new PropertyValueFactory<hole,Integer>("size"));
        AllocatedBlockStart.setCellValueFactory(new PropertyValueFactory<process,Integer>("startAddress"));
        AllocatedBlockSize.setCellValueFactory(new PropertyValueFactory<process,Integer>("size"));
        AllocatedBlockProcess.setCellValueFactory(new PropertyValueFactory<process,String>("Name"));
        GridPane grid=new GridPane();
        Holes.setItems(Hole);
        AllocatedMemory.setItems(Process);
        Holes.getColumns().addAll(HoleStart,HoleSize);
        AllocatedMemory.getColumns().addAll(AllocatedBlockStart,AllocatedBlockSize,AllocatedBlockProcess);
        AllocatedMemory.setMinWidth(100);


        // grid.setColumnSpan(AllocatedMemory,2);
        grid.add(Holes,0,0);
        grid.add(AllocatedMemory,1,0);
        grid.setHgrow(AllocatedMemory, Priority.ALWAYS);
        scene = new Scene(grid,800,300);
        window.setScene(scene);
        window.show();

    }
    public static void close()
    {

    }
}
