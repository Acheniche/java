package post.post3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class isEmpty {
    @FXML
    public Button closeButton;

    @FXML
    private void btnClickCancel() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
