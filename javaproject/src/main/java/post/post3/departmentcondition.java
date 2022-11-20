package post.post3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class departmentcondition {
    @FXML
    public Button closeButton;
    @FXML
    private void btnClickCancel() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

@FXML
public TextField result;

    @FXML
private void ViewPacks(){
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
      if(Post.getSelectionModel().getSelectedItem().equals(d.name)){
          result.setText("Small: "+ d.smallpack + " Medium: " + d.mediumpack + " Big: " + d.bigpack);
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
}
