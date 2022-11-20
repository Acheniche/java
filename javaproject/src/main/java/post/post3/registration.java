package post.post3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class registration {
    @FXML
    public Button closeButton;
    @FXML
    private void btnClickCancel() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public Button ButtonRegistr;
    @FXML
    public TextField enternumberofhouse;
    @FXML
    public TextField enternumberofflat;
    @FXML
    public  TextField entername;

    @FXML
    public TextField enterstreet;
    boolean exist = false;
    boolean number1 = false;
    boolean number2 = false;

    private static ArrayList<Packinfo> theList1;
    @FXML
    private void BtnClickRegistr() throws IOException{
        isExist();
        isNumeric1();
        isNumeric2();
        if(Post.getSelectionModel().isEmpty()||enterstreet.getText().isEmpty()||sizepos==-1||enternumberofhouse.getText().isEmpty()||enternumberofflat.getText().isEmpty()||entername.getText().isEmpty()){
            try {
                Parent anotherRoot = FXMLLoader.load(getClass().getResource("isEmpty.fxml"));
                Stage anotherStage = new Stage();
                anotherStage.setTitle("Error");
                anotherStage.setScene(new Scene(anotherRoot, 200, 100));
                anotherStage.show();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(exist == false||number1 == false||number2 == false){
            try {
                Parent anotherRoot = FXMLLoader.load(getClass().getResource("notExist.fxml"));
                Stage anotherStage = new Stage();
                anotherStage.setTitle("Error");
                anotherStage.setScene(new Scene(anotherRoot, 300, 100));
                anotherStage.show();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            File theFIle = new File("Departments.json");
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(theFIle);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Gson gson = new Gson();
            theList = new ArrayList<>();

            Type type = new TypeToken<ArrayList<Departments>>() {
            }.getType();
            theList = gson.fromJson(fileReader, type);
            try {
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Departments d : theList) {
                if (Post.getSelectionModel().getSelectedItem().equals(d.name)) {
                    if(sizepos==0){
                      d.smallpack++;
                    }
                    if(sizepos==1){
                        d.mediumpack++;
                    }
                    if(sizepos==2){
                        d.bigpack++;
                    }
                    FileWriter fileWriter = new FileWriter(theFIle);
                    gson.toJson(theList, fileWriter);
                    fileWriter.close();
                }
            }

            //-----------------------------------------------------

            File theFIle1 = new File("packinfo.json");
            FileReader fileReader1 = new FileReader(theFIle1);
            Gson gson1 = new Gson();
            theList1 = new ArrayList<>();
            Type type1 = new TypeToken<ArrayList<Packinfo>>() {}.getType();
            theList1 = gson1.fromJson(fileReader1, type1);
            fileReader1.close();
            String post = Post.getSelectionModel().getSelectedItem().toString();
            theList1.add (new  Packinfo(post,enterstreet.getText(),enternumberofhouse.getText(),enternumberofflat.getText(),size,entername.getText(),RandomStringUtils.randomAlphanumeric(10)));
            FileWriter fileWriter1 = new FileWriter(theFIle1);
            gson1.toJson(theList1, fileWriter1);
            fileWriter1.close();


            //________________------------------------------------
        }
    }

    public void isNumeric1() {
        try {
            Long.parseLong(enternumberofhouse.getText());
            number1 = true;
        } catch(NumberFormatException e) {
            number1 = false;
        }
    }

    public void isNumeric2() {
        try {
            Long.parseLong(enternumberofflat.getText());
            number2 = true;
        } catch(NumberFormatException e) {
            number2 = false;
        }
    }

    @FXML
    private RadioButton rButton1, rButton2, rButton3;

    int sizepos =-1;
    String size ="";
    public void getSize() {
        if(rButton1.isSelected()) {
            sizepos = 0;
            size = "small";
        }
        else if(rButton2.isSelected()) {
            sizepos = 1;
            size = "medium";
        }
        else if(rButton3.isSelected()) {
            sizepos = 2;
            size = "Big";
        }
    }

    @FXML
    public ComboBox Post;
    private static ArrayList<Departments> theList;
    public void initialize() {
        File theFIle = new File("Departments.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(theFIle);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        theList = new ArrayList<>();

        Type type = new TypeToken<ArrayList<Departments>>() {
        }.getType();
        theList = gson.fromJson(fileReader, type);
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Departments d : theList) {
            Post.getItems().addAll(d.name);
        }
    }

    public void isExist() {
        File theFIle = new File("Departments.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(theFIle);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        theList = new ArrayList<>();

        Type type = new TypeToken<ArrayList<Departments>>() {
        }.getType();
        theList = gson.fromJson(fileReader, type);
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Departments d : theList) {
            if(d.name.equals(Post.getSelectionModel().getSelectedItem()) && d.streets.contains(String.valueOf(enterstreet.getText())))
             {
                exist = true;
            }
        }
    }

}
