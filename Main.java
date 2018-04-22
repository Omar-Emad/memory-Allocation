package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.image.SampleModel;
import java.util.Vector;

public class Main extends Application implements EventHandler<ActionEvent> {
    GridPane Main=new GridPane();
    boolean saved=false;
    GridPane AllProcesses=new GridPane();
    GridPane AllHoles=new GridPane();
    Label noOfProcessesLabel=new Label("No. of Processes:");
    TextField noOfProcessesTextField=new TextField();
    HBox Process=new HBox();
    HBox Hole=new HBox();
    Label noOfHolesLabel = new Label("No. of Holes :");
    TextField noOfHolesTextField=new TextField();
    int noProcesses ;
    int noHoles;
    ScrollPane ss;
    ScrollPane sa;
    Label SavingData=new Label("Press Save button To Save the contents of the Tables before allocation");
    Button Save=new Button();
    TextField Allocation=new TextField();
    TextField DeAllocation=new TextField();
    Button Allocate=new Button();
    Button DeAllocate =new Button();
    ToggleGroup Options=new ToggleGroup();
    Label options=new Label("Choose the Method of Allocation");
    RadioButton FirstFit=new RadioButton("FirstFit");
    RadioButton BestFit=new RadioButton("BestFit");
    Vector<process>Processes=new Vector<process>();
    Vector<hole> Holes=new Vector<hole>();
    Vector<process> Allocatedprocesses=new Vector<process>();
    Stage window=new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{


        Main.setPadding(new Insets(30, 30, 30, 30));
        Main.setVgap(20);
        Main.setHgap(20);
        noOfProcessesLabel.setFont(new Font("Arial", 17));
        noOfProcessesLabel.setStyle("-fx-text-fill: linear-gradient(#ce1212,#e0580f);");
        noOfHolesLabel.setFont(new Font("Arial", 17));
        noOfHolesLabel.setStyle("-fx-text-fill: linear-gradient(#ce1212,#e0580f);");
        Hole.getChildren().addAll(noOfHolesLabel,noOfHolesTextField);
        Process.getChildren().addAll(noOfProcessesLabel,noOfProcessesTextField);
        SavingData.setFont(new Font("Arial", 17));
        SavingData.setStyle("-fx-text-fill: linear-gradient(#ce1212,#e0580f);");
        options.setFont(new Font("Arial", 17));
        options.setStyle("-fx-text-fill: linear-gradient(#ce1212,#e0580f);");
        Save.setText("Save");
        Allocate.setText("Allocate");
        Save.setOnAction(this);
        DeAllocate.setText("DeAllocate");
        Allocate.setOnAction(this);
        DeAllocate.setOnAction(this);
        Allocation.setPromptText("Enter Process Name to Allocate ");
        DeAllocation.setPromptText("Enter Process Name to DeAllocate ");
        FirstFit.setToggleGroup(Options);
        BestFit.setToggleGroup(Options);
        Main.add(Hole,0,0);
        Main.add(Process,1,0);
        Main.add(options,0,2);
        Main.add(FirstFit,1,2);
        Main.add(BestFit,2,2);
        Main.add(SavingData,0,3);
        Main.add(Save,1,3);
        Main.add(Allocation,0,4);
        Main.add(Allocate,1,4);
        Main.add(DeAllocation,0,5);
        Main.add(DeAllocate,1,5);
        noOfHolesTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {



                AllHoles.getChildren().clear();
                try {noHoles=Integer.parseInt(noOfHolesTextField.getText());}
                catch (NumberFormatException e)
                {noHoles=0;}
                Label size=new Label("Hole Size");
                Label start=new Label("        Hole Starting Address        ");
                Label l =new Label("   ");
                HBox v=new HBox();
                v.getChildren().addAll(l,start,size);
                AllHoles.add(v,0,0);
                for (int i = 1; i <=noHoles ; i++) {
                    TextField Start =new TextField();
                    TextField Size=new TextField() ;
                    Label ll =new Label(Integer.toString(i));
                    HBox vv=new HBox();
                    vv.getChildren().addAll(ll,Start,Size);
                    AllHoles.add(vv,0,i);


                }
                 ss=new ScrollPane(AllHoles);
                ss.setFitToHeight(true);
                Main.add(ss,0,1);
            }
        });
        noOfProcessesTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                AllProcesses.getChildren().clear();
                try {noProcesses=Integer.parseInt(noOfProcessesTextField.getText());}
                catch (NumberFormatException e)
                {noProcesses=0;}
                Label size=new Label("Process Size");
                Label name=new Label("        Process Name              ");
                Label l =new Label("   ");
                HBox v=new HBox();
                v.getChildren().addAll(l,name,size);
                AllProcesses.add(v,0,0);
                for (int i = 1; i <=noProcesses ; i++) {
                    TextField Name =new TextField();
                    TextField Size=new TextField() ;
                    Label ll =new Label(Integer.toString(i));
                    HBox vv=new HBox();
                    vv.getChildren().addAll(ll,Name,Size);
                    AllProcesses.add(vv,0,i);


                }
                sa=new ScrollPane(AllProcesses);
                sa.setFitToHeight(true);
                Main.add(sa,1,1);
            }
        });

        primaryStage.setTitle("Memory Allocation");
        primaryStage.setScene(new Scene(Main, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public void sort(Vector<hole> holes, String method) {   // "FF" for first fit & "BF" for best fit
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

      if(event.getSource()==Save)
      {
          saved=true;
          Processes.clear();
          Holes.clear();
          for (int i = 1; i <=noProcesses ; i++)
          {  process buffer=new process();
              HBox b=new HBox();
              Node hbox=AllProcesses.getChildren().get(i);
              if(hbox instanceof HBox) {
                  Node counter = ((HBox) hbox).getChildren().get(1);
                  if (counter instanceof TextField) {
                      buffer.setName(((TextField) counter).getText());
                  }
                  counter = ((HBox) hbox).getChildren().get(2);
                  if (counter instanceof TextField) {
                      try{buffer.setSize(Integer.parseInt(((TextField) counter).getText()));}
                      catch(NumberFormatException e)
                      {
                          buffer.setSize(0);
                      }
                  }
              }
              Processes.add(buffer);

          }
          for (int i = 1; i <=noHoles ; i++)
          {  hole buffer=new hole();
              HBox b=new HBox();
              Node hbox=AllHoles.getChildren().get(i);
              if(hbox instanceof HBox) {
                  Node counter = ((HBox) hbox).getChildren().get(1);
                  if (counter instanceof TextField) {
                     try{ buffer.setStartAddress(Integer.parseInt(((TextField) counter).getText()));}
                     catch(NumberFormatException e)
                     {
                         buffer.setStartAddress(0);
                     }
                  }
                  counter = ((HBox) hbox).getChildren().get(2);
                  if (counter instanceof TextField) {
                      try{buffer.setSize(Integer.parseInt(((TextField) counter).getText()));}
                      catch(NumberFormatException e) {
                          buffer.setSize(0);
                      }
                  }
              }
              Holes.add(buffer);

          }


      }
      if(event.getSource()==Allocate) {
          if (saved == false) {
              Label l = new Label("Fill Tables and Press Save first befora allocation");
              AlertBox1.display(l);
          } else {
              int toBeAllocated = 0;
              boolean FoundProcess = false;
              String Name = Allocation.getText();
              boolean Allocated = false;
              for (int i = 0; i < noProcesses; i++) {
                  if (Processes.elementAt(i).getName().equals(Name)) {
                      toBeAllocated = i;
                      FoundProcess = true;
                      break;
                  }
              }
              if (FoundProcess == false) {
                  Label l = new Label("No Process with such Name is Found");
                  AlertBox1.display(l);
              } else if (Processes.elementAt(toBeAllocated).getStartAddress() != -1) {
                  Label l = new Label("This Process is already allocated");
                  AlertBox1.display(l);
              } else if (FirstFit.isSelected()) {
                  sort(Holes, "FF");
                  for (int i = 0; i < noHoles; i++) {
                      if (Holes.elementAt(i).getSize() >= Processes.elementAt(toBeAllocated).getSize()) {
                          Allocated = true;
                          Allocatedprocesses.add(Processes.elementAt(toBeAllocated));
                          Processes.elementAt(toBeAllocated).setStartAddress(Holes.elementAt(i).getStartAddress());
                          if (Holes.elementAt(i).getSize() == Processes.elementAt(toBeAllocated).getSize()) {
                              Holes.remove(i);
                          } else {
                              Holes.elementAt(i).setSize(Holes.elementAt(i).getSize() - Processes.elementAt(toBeAllocated).getSize());
                          }
                          break;
                      }
                  }
                  if (Allocated == false) {
                      Label l = new Label("No Holes has the required size for this process");
                      AlertBox1.display(l);
                  } else {
                      window.close();
                      ResultWindow.display(Holes, Allocatedprocesses, window);
                  }
              } else if (BestFit.isSelected()) {
                  sort(Holes, "BF");
                  for (int i = 0; i < noHoles; i++) {
                      if (Holes.elementAt(i).getSize() >= Processes.elementAt(toBeAllocated).getSize()) {
                          Allocated = true;
                          Allocatedprocesses.add(Processes.elementAt(toBeAllocated));
                          Processes.elementAt(toBeAllocated).setStartAddress(Holes.elementAt(i).getStartAddress());
                          if (Holes.elementAt(i).getSize() == Processes.elementAt(toBeAllocated).getSize()) {
                              Holes.remove(i);
                          } else {
                              Holes.elementAt(i).setSize(Holes.elementAt(i).getSize() - Processes.elementAt(toBeAllocated).getSize());
                          }
                          break;
                      }
                  }
                  if (Allocated == false) {
                      Label l = new Label("No Holes has the required size for this process");
                      AlertBox1.display(l);
                  } else {
                      window.close();
                      ResultWindow.display(Holes, Allocatedprocesses, window);

                  }
              }
          }
      }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
