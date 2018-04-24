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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.layout.StackPane;


public class Main extends Application implements EventHandler<ActionEvent> {
    GridPane Main=new GridPane();
    boolean saved=false;
    GridPane AllProcesses=new GridPane();
    GridPane AllHoles=new GridPane();
    Label noOfProcessesLabel=new Label("No. of Processes   ");
    TextField noOfProcessesTextField=new TextField();
    HBox Process=new HBox();
    HBox Hole=new HBox();
    Label noOfHolesLabel = new Label("No. of Holes   ");
    TextField noOfHolesTextField=new TextField();
    int noProcesses ;
    int noHoles;
    int memorySize;
    ScrollPane ss;
    ScrollPane sa;
    Label SavingData=new Label("Press Save button To Save the contents of the Tables before allocation");
    Button Save=new Button();
    ComboBox Allocation=new ComboBox();
    ComboBox DeAllocation=new ComboBox();
    Button Allocate=new Button();
    Button DeAllocate =new Button();
    ToggleGroup Options=new ToggleGroup();
    Label options=new Label("Allocation Method");
    RadioButton FirstFit=new RadioButton("FirstFit");
    RadioButton BestFit=new RadioButton("BestFit");
    Vector<process>Processes=new Vector<process>();
    Vector<hole> Holes=new Vector<hole>();
    TextField memorySizeText =new TextField();
    Label memorySizeLabel = new Label("Memory Size  ");
    HBox memorysizeBox = new HBox();

    Vector<process> Allocatedprocesses=new Vector<process>();
    ArrayList<String> names = new ArrayList<String >();
    Stage window=new Stage();

    String AllocatedName;

    @Override
    public void start(Stage primaryStage) throws Exception{


        Allocation.setEditable(false);
        DeAllocation.setEditable(false);
        Allocation.setPromptText("Allocated Process Name");
        DeAllocation.setPromptText("DeAllocated Process Name");
        Main.setPadding(new Insets(40, 40, 40, 40));
        Main.setVgap(20);
        Main.setHgap(30);
        Main.setStyle("-fx-background-color: #eae5e5;");
        noOfProcessesLabel.setStyle("-fx-font: 20 arial;  -fx-text-fill: #8f12ce; -fx-font-weight: bold;");
        noOfHolesLabel.setStyle("-fx-font: 20 arial;  -fx-text-fill: #8f12ce; -fx-font-weight: bold;");
        memorySizeLabel.setStyle("-fx-font: 20 arial;  -fx-text-fill: #8f12ce; -fx-font-weight: bold;");
        noOfHolesTextField.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
        noOfProcessesTextField.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
        memorySizeText.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
        FirstFit.setStyle("-fx-font: 16 arial;  -fx-text-fill: #000000;");
        BestFit.setStyle("-fx-font: 16 arial;  -fx-text-fill: #000000;");
        Hole.getChildren().addAll(noOfHolesLabel,noOfHolesTextField);
        Process.getChildren().addAll(noOfProcessesLabel,noOfProcessesTextField);
        SavingData.setFont(new Font("Arial", 17));
        options.setStyle("-fx-font: 20 arial;  -fx-text-fill: #8f12ce; -fx-font-weight: bold;");
        Save.setStyle("-fx-font: 17 arial; -fx-background-color: linear-gradient(#8f12ce,#eae5e5) ;  -fx-text-fill: #1c1b1b; -fx-background-radius: 7;");
        Allocate.setStyle("-fx-font: 17 arial; -fx-background-color: linear-gradient(#8f12ce,#eae5e5) ;  -fx-text-fill: #1c1b1b; -fx-background-radius: 7;");
        DeAllocate.setStyle("-fx-font: 17 arial; -fx-background-color: linear-gradient(#8f12ce,#eae5e5) ;  -fx-text-fill: #1c1b1b; -fx-background-radius: 7;");
        Save.setText("Save");
        Allocate.setText("Allocate");
        Save.setOnAction(this);
        DeAllocate.setText("DeAllocate");
        Allocate.setOnAction(this);
        DeAllocate.setOnAction(this);
        Allocation.setPromptText("Allocate Process ");
        Allocation.setStyle("-fx-font: 15 arial; ");
        DeAllocation.setPromptText("DeAllocate Process ");
        DeAllocation.setStyle("-fx-font: 15 arial; ");
        FirstFit.setToggleGroup(Options);
        BestFit.setToggleGroup(Options);
        memorysizeBox.getChildren().addAll(memorySizeLabel,memorySizeText);

        Main.add(Hole,0,5);
        Main.add(Process,3,5);
        Main.add(options,0,0);
        Main.add(memorysizeBox,0,4);
        Main.add(FirstFit,0,1);
        Main.add(BestFit,0,2);
        Main.add(Save,1,9);
        Main.add(Allocation,0,10);
        Main.add(Allocate,0,11);
        Main.add(DeAllocation,0,12);
        Main.add(DeAllocate,0,13);
        noOfHolesTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {



                AllHoles.getChildren().clear();
                AllHoles.setVgap(10);
                AllHoles.setHgap(10);
                AllHoles.setPadding(new Insets(10,10,10,10));
                try {noHoles=Integer.parseInt(noOfHolesTextField.getText());}
                catch (NumberFormatException e)
                {noHoles=0;}
                Label size=new Label(" Hole Size");
                size.setStyle("-fx-font: 16 arial;  -fx-text-fill: #000000; ");
                Label start=new Label("      Hole Starting Address             ");
                start.setStyle("-fx-font: 16 arial;  -fx-text-fill: #000000; ");
                HBox v=new HBox();
                v.getChildren().addAll(start,size);
                AllHoles.add(v,0,0);
                for (int i = 1; i <=noHoles ; i++) {
                    TextField Start =new TextField();
                    Start.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
                    TextField Size=new TextField() ;
                    Size.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
                    Label ll =new Label(Integer.toString(i));
                    ll.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
                    HBox vv=new HBox();
                    vv.getChildren().addAll(ll,Start,Size);
                    vv.setSpacing(13);
                    AllHoles.add(vv,0,i);


                }
                ss=new ScrollPane(AllHoles);
               // ss.setFitToHeight(true);
                Main.add(ss,0,6);
            }
        });
        noOfProcessesTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                names.clear();
                AllHoles.setPadding(new Insets(10,10,10,10));
                AllProcesses.getChildren().clear();
                AllProcesses.setVgap(10);
                AllProcesses.setPadding(new Insets(10,10,10,10));
                try {noProcesses=Integer.parseInt(noOfProcessesTextField.getText());}
                catch (NumberFormatException e)
                {noProcesses=0;}
                Label size=new Label("Process Size");
                size.setStyle("-fx-font: 16 arial;  -fx-text-fill: #000000; ");
                Label name=new Label("Name           ");
                name.setStyle("-fx-font: 16 arial;  -fx-text-fill: #000000; ");
                HBox v=new HBox();
                v.getChildren().addAll(name,size);
                AllProcesses.add(v,0,0);
                for (int i = 1; i <=noProcesses ; i++) {
                    Label Name =new Label ("P"+(i-1));
                    Name.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
                    names.add("P"+(i-1));
                    TextField Size=new TextField() ;
                    Size.setStyle("-fx-font: 15 arial;  -fx-text-fill: #000000; ");
                    HBox vv=new HBox();
                    vv.getChildren().addAll(Name,Size);
                    vv.setSpacing(40);
                    AllProcesses.add(vv,0,i);


                }
                sa=new ScrollPane(AllProcesses);
                sa.setFitToHeight(true);
                Main.add(sa,3,6);
            }
        });

        ScrollPane sp =new ScrollPane();
        sp.setContent(Main);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        primaryStage.setTitle("Memory Allocation");
        primaryStage.setScene(new Scene(sp, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(300);
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

//============== save ==============================================================================

        if(event.getSource()==Save)
        {
            saved=true;
            Processes.clear();
            Holes.clear();
            Allocatedprocesses.clear();
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
            else if(memorySizeText.getText().equals(""))
            {
                Label l=new Label("Please Enter the Memory Size");
                AlertBox1.display(l);
            }
            else
            {
                memorySize=Integer.parseInt(memorySizeText.getText());

                ObservableList<String> Process =
                        FXCollections.observableArrayList(names);
                ObservableList<String> deprocess =
                        FXCollections.observableArrayList();

                int no=0;
                if(Holes.size()>=1&&Holes.get(0).getStartAddress()!=0)
                {
                    process buffer = new process(Holes.get(0).getStartAddress(),0,"Block"+no);
                    deprocess.add(buffer.getName());
                    Allocatedprocesses.add(buffer);
                    no++;
                }
                for(int i=0;i<Holes.size();i++)
                {
                    if (i==Holes.size()-1)
                    {
                        process buffer = new process(memorySize-Holes.get(i).getEndAddress(),Holes.get(i).getEndAddress()+1,"Block"+no);
                        deprocess.add(buffer.getName());
                        Allocatedprocesses.add(buffer);
                    }
                    else
                    {
                        process buffer = new process(Holes.get(i+1).getStartAddress()-Holes.get(i).getEndAddress()-1,Holes.get(i).getEndAddress()+1,"Block"+no);
                        deprocess.add(buffer.getName());
                        Allocatedprocesses.add(buffer);
                    }
                    no++;
                }

                Allocation.setItems(Process);
                DeAllocation.setItems(deprocess);

            }

//============== Allocate ==============================================================================
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
                else {
                    Label l = new Label("Please shows Allocation Method");
                    AlertBox1.display(l);
                }
            }
        }
//============== Deallocate ==============================================================================

        if(event.getSource()==DeAllocate)
        {
            String userIn=DeAllocation.getValue().toString();
            boolean holeBefore=false; boolean holeAfter=false;
            int holes[]=new int[2];   //holes[0] is the index of the hole before process  holes[1] is the index of hole after
            for(int i=0;i<Allocatedprocesses.size();i++)
            {
                if(Allocatedprocesses.get(i).getName().equals(userIn))
                {
                    for(int j=0;j<Holes.size();j++)
                    {
                        if(Allocatedprocesses.get(i).getStartAddress()==Holes.get(j).getEndAddress()+1)
                        {
                            holeBefore=true;
                            holes[0]=j;
                        }
                        if(Allocatedprocesses.get(i).getEndAddress()==Holes.get(j).getStartAddress()-1)
                        {
                            holeAfter=true;
                            holes[1]=j;
                        }
                    }
                    if(holeBefore&&holeAfter)  // there is hole before and after it
                    {
                        Holes.get(holes[0]).setSize(Holes.get(holes[0]).getSize()+
                                Holes.get(holes[1]).getSize()+ Allocatedprocesses.get(i).getSize());   //set the size of this hole= size of this hole + size of process + size of the hole after the process
                        Holes.remove(holes[1]);   //remove the hole after the process
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
