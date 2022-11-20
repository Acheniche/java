package post.post3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class newstreet {
    @FXML
    public Button closeButton;
    @FXML
    private void btnClickCancel() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public Button addButton;
    @FXML
    public TextField enterstreet;
    boolean exist = false;
    @FXML
    private void btnClickAdd() throws IOException {
        isExist();
        if(exist == true){
            try {
                Parent anotherRoot = FXMLLoader.load(getClass().getResource("isExistError.fxml"));
                Stage anotherStage = new Stage();
                anotherStage.setTitle("Error");
                anotherStage.setScene(new Scene(anotherRoot, 280, 100));
                anotherStage.show();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(Post.getSelectionModel().isEmpty()||enterstreet.getText().isEmpty()){
            try {
                Parent anotherRoot = FXMLLoader.load(getClass().getResource("isEmpty.fxml"));
                Stage anotherStage = new Stage();
                anotherStage.setTitle("Error");
                anotherStage.setScene(new Scene(anotherRoot, 160, 100));
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
                    d.streets.add(enterstreet.getText());
                    FileWriter fileWriter = new FileWriter(theFIle);
                    gson.toJson(theList, fileWriter);
                    fileWriter.close();
                }
            }
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

           if(d.streets.contains(String.valueOf(enterstreet.getText()))) {
                  exist = true;
           }
        }
    }
}
