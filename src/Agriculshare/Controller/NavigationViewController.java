package Agriculshare.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NavigationViewController {

    @FXML
    private ImageView DashboardView;

    @FXML
    private ImageView PoolView;

    @FXML
    private ImageView VacancyView;

    @FXML
    void SwitchScene(MouseEvent event) {
        switch (((ImageView)event.getSource()).getId()){
            case "DashboardView": SwitchScenes.DASHBOARD.SwitchScene(event); break;
            case "PoolView": SwitchScenes.POOL.SwitchScene(event); break;
            case "VacancyView": SwitchScenes.VACANCY.SwitchScene(event);break;
            default: SwitchScenes.DASHBOARD.SwitchScene(event);break;
        }
    }

    @FXML
    void logout(MouseEvent event) {
        System.exit(1);
    }

}