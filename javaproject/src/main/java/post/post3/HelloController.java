package post.post3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private void btnClickCancel() {
        System.exit(0);
    }
    @FXML
    public Button Buttondepartment;
    @FXML
    private void Buttondepartment(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("newdepartment.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Добавление отделения");
            anotherStage.setScene(new Scene(anotherRoot, 360, 280));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public Button Buttonstreet;
    @FXML
    private void Buttonstreet(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("newstreet.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Добавление улицы");
            anotherStage.setScene(new Scene(anotherRoot, 350, 260));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public Button Buttoncondition;
    @FXML
    private void Buttoncondition(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("departmentcondition.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Состояние отделения");
            anotherStage.setScene(new Scene(anotherRoot, 330, 240));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public Button Buttonregistration;
    @FXML
    private void Buttonregistration(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("registration.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Регистрация посылки");
            anotherStage.setScene(new Scene(anotherRoot, 550, 280));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
