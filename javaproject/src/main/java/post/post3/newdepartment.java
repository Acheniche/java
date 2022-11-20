package post.post3;

import java.io.FileWriter;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.*;
import com.google.gson.Gson;
import java.util.*;
import java.lang.reflect.*;
import java.io.FileNotFoundException;

public class newdepartment {

    @FXML
    public Button closeButton;

    @FXML
    private void btnClickCancel() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TextArea viewdepartments;

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
            viewdepartments.appendText(d.name + "\n");
        }
    }

    @FXML
    private TextField enterdepartment;
    String enterpost = "";
    private static ArrayList<Departments> theList;
    boolean exist = false;
    @FXML
    private void btnClickAdd() throws IOException {
        isExist();
        if(exist == true){
            try {
                Parent anotherRoot = FXMLLoader.load(getClass().getResource("isExistError.fxml"));
                Stage anotherStage = new Stage();
                anotherStage.setTitle("Error");
                anotherStage.setScene(new Scene(anotherRoot, 230, 100));
                anotherStage.show();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        else  {
    File theFIle = new File("Departments.json");
    FileReader fileReader = new FileReader(theFIle);
    Gson gson = new Gson();
    theList = new ArrayList<>();
    Type type = new TypeToken<ArrayList<Departments>>() {}.getType();
    theList = gson.fromJson(fileReader, type);
    fileReader.close();
    theList.add(new Departments(enterdepartment.getText(), 0, 0, 0, Arrays.asList()));
    FileWriter fileWriter = new FileWriter(theFIle);
    gson.toJson(theList, fileWriter);
    fileWriter.close();
    viewdepartments.setText("");
    initialize();
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
            if(d.name.equals(enterdepartment.getText())){
                 exist = true;
            }
        }
    }
}
