package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
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
    ComboBox Allocation=new ComboBox();
    ComboBox DeAllocation=new ComboBox();
    Button Allocate=new Button();
    Button DeAllocate =new Button();
    ToggleGroup Options=new ToggleGroup();
    Label options=new Label("Choose the Method of Allocation");
    RadioButton FirstFit=new RadioButton("FirstFit");
    RadioButton BestFit=new RadioButton("BestFit");
    Vector<process>Processes=new Vector<process>();
    Vector<hole> Holes=new Vector<hole>();

    Vector<process> Allocatedprocesses=new Vector<process>();
    ArrayList<String> names = new ArrayList<String >();
    Stage window=new Stage();

    String AllocatedName;
    String DeAllocatedName;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Allocation.setEditable(false);
        DeAllocation.setEditable(false);
        Allocation.setPromptText("Allocated Process Name");
        DeAllocation.setPromptText("DeAllocated Process Name");
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
                names.clear();
                AllProcesses.getChildren().clear();
                try {noProcesses=Integer.parseInt(noOfProcessesTextField.getText());}
                catch (NumberFormatException e)
                {noProcesses=0;}
                Label size=new Label("Process Size");
                Label name=new Label("Process Name    ");
                HBox v=new HBox();
                v.getChildren().addAll(name,size);
                AllProcesses.add(v,0,0);
                for (int i = 1; i <=noProcesses ; i++) {
                    Label Name =new Label ("P"+(i-1));
                    names.add("P"+(i-1));
                    TextField Size=new TextField() ;
                    HBox vv=new HBox();
                    vv.getChildren().addAll(Name,Size);
                    vv.setSpacing(80);
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
            for (int i = 1; i <= noProcesses; i++) {
                process buffer = new process();
                HBox b = new HBox();
                Node hbox = AllProcesses.getChildren().get(i);
                if (hbox instanceof HBox) {
                    Node counter = ((HBox) hbox).getChildren().get(0);
                    if (counter instanceof Label) {
                        buffer.setName(((Label) counter).getText());
                    }
                    counter = ((HBox) hbox).getChildren().get(1);
                    if (counter instanceof TextField) {
                        try {
                            buffer.setSize(Integer.parseInt(((TextField) counter).getText()));
                        } catch (NumberFormatException e) {
                            buffer.setSize(0);
                        }
                    }
                }
                Processes.add(buffer);

            }
            for (int i = 1; i <= noHoles; i++) {
                hole buffer = new hole();
                HBox b = new HBox();
                Node hbox = AllHoles.getChildren().get(i);
                if (hbox instanceof HBox) {
                    Node counter = ((HBox) hbox).getChildren().get(1);
                    if (counter instanceof TextField) {
                        try {
                            buffer.setStartAddress(Integer.parseInt(((TextField) counter).getText()));
                        } catch (NumberFormatException e) {
                            buffer.setStartAddress(0);
                        }
                    }
                    counter = ((HBox) hbox).getChildren().get(2);
                    if (counter instanceof TextField) {
                        try {
                            buffer.setSize(Integer.parseInt(((TextField) counter).getText()));
                        } catch (NumberFormatException e) {
                            buffer.setSize(0);
                        }
                    }
                }
                Holes.add(buffer);

            }
            sort(Holes,"FF");
            boolean error=false;
            for (int i = 0; i <noHoles-1 ; i++) {
                for (int j = i+1; j <noHoles ; j++) {
                    if(Holes.elementAt(i).getEndAddress()>Holes.elementAt(j).getStartAddress())
                    {
                        error=true;
                        break;
                    }
                }
            }
            if(error==true){ Label l = new Label("Holes Overlap ! Please enter the right starts and sizes of holes");
                AlertBox1.display(l);
                saved =false;
            }
            else
            {

                ObservableList<String> Process =
                        FXCollections.observableArrayList(names);
                Allocation.setItems(Process);
                DeAllocation.setItems(Process);

            }


        }
        if(event.getSource()==Allocate) {
            if (saved == false) {
                Label l = new Label("Fill Tables  and Press Save first before allocation");
                AlertBox1.display(l);
            } else {
                int toBeAllocated = 0;
                boolean FoundProcess = false;
                AllocatedName=Allocation.getValue().toString();
                boolean Allocated = false;
                for (int i = 0; i < noProcesses; i++) {
                    if (Processes.elementAt(i).getName().equals(AllocatedName)) {
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
                                Holes.elementAt(i).setStartAddress(Holes.elementAt(i).getStartAddress() + Processes.elementAt(toBeAllocated).getSize());
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
                                Holes.elementAt(i).setStartAddress(Holes.elementAt(i).getStartAddress() + Processes.elementAt(toBeAllocated).getSize());
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
        if(event.getSource()==DeAllocate)
        {
            String userIn=DeAllocation.getValue().toString();
            boolean holeBefore=false; boolean holeAfter=false;
            int holes[]=new int[2];   //holes[0] is the index of the hole before process  holes[1] is the hole after
            System.out.println("Deallocate");
            for(int i=0;i<Allocatedprocesses.size();i++)
            {System.out.println(userIn+"     "+Allocatedprocesses.get(i).getName());
                if(Allocatedprocesses.get(i).getName().equals(userIn))
                {System.out.println(Allocatedprocesses.get(i).getName());
                    for(int j=0;j<Holes.size();j++)
                    {
                        if(Allocatedprocesses.get(i).getStartAddress()==Holes.get(j).getEndAddress()+1)
                        {
                            holeBefore=true;
                            holes[0]=j;
                            System.out.println("before");
                        }
                        if(Allocatedprocesses.get(i).getEndAddress()==Holes.get(j).getStartAddress()-1)
                        {
                            holeAfter=true;
                            holes[1]=j;
                            System.out.println("After");
                        }
                    }
                    if(holeBefore&&holeAfter)  // there is hole before and after it
                    {
                        Holes.get(holes[0]).setSize(Holes.get(holes[0]).getSize()+
                                Holes.get(holes[1]).getSize()+ Allocatedprocesses.get(i).getSize());   //set the size of this hole= size of this hole + size of process + size of the hole after the process
                        Holes.remove(holes[1]);   //remove the hole after the process
                        System.out.println("before and after");
                    }
                    else if(holeBefore)
                    {
                        Holes.get(holes[0]).setSize(Holes.get(holes[0]).getSize()+Allocatedprocesses.get(i).getSize());
                    }
                    else if(holeAfter)
                    {
                        Holes.get(holes[1]).setSize(Holes.get(holes[1]).getSize()+Allocatedprocesses.get(i).getSize());
                        Holes.get(holes[1]).setStartAddress(Allocatedprocesses.get(i).getStartAddress());
                    }
                    else
                    {
                        hole buffer=new hole(Allocatedprocesses.get(i).getStartAddress(),Allocatedprocesses.get(i).getSize());
                        Holes.add(buffer);
                        System.out.println("new hole");
                    }

                    Allocatedprocesses.get(i).setStartAddress(-1);

                    Allocatedprocesses.remove(i);

                    sort(Holes,"FF");

                    break;
                }
            }
            window.close();
            ResultWindow.display(Holes, Allocatedprocesses, window);

        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
