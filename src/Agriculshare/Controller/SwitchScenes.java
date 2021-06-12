package Agriculshare.Controller;

import Agriculshare.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Agriculshare.Application;

import java.io.IOException;

public enum SwitchScenes {
    LOGIN("LoginView.fxml"),
    DASHBOARD("DashboardView.fxml"),
    VACANCY("VacancyView.fxml"),
    POOL("PoolView.fxml"),
    REGISTER("RegisterView.fxml");

    private String filename, root = "/Agriculshare/View/";

    SwitchScenes(String location) {
        this.filename = location;
    }

    String getLocation() {
        return root + this.filename;
    }

    private double xOffset = 0, yOffset = 0;

    public void SwitchScene(ActionEvent e) {
        //if (Application.user != null && Application.user.isRegistered()) {Application.user.SaveUser();}

        try {
            System.out.println(this.getLocation());
            Parent SceneParent = FXMLLoader.load(getClass().getResource(this.getLocation()));
            Scene Scene = new Scene(SceneParent);

            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(Scene);
            window.show();

            SceneParent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    xOffset = mouseEvent.getSceneX();
                    yOffset = mouseEvent.getSceneY();
                }
            });

            SceneParent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    window.setX(mouseEvent.getScreenX() - xOffset);
                    window.setY(mouseEvent.getScreenY() - yOffset);
                }
            });

        } catch (IOException ioException) {
            System.err.println("Unable to load scene " + this.getLocation());
            ioException.printStackTrace();
        }
    }

    public void SwitchScene(MouseEvent e) {

        //if (Application.user != null && Application.user.isRegistered()) {Application.user.SaveUser();}

        try {
            System.out.println(this.getLocation());
            Parent sceneParent = FXMLLoader.load(getClass().getResource(this.getLocation()));
            Scene scene = new Scene(sceneParent);

            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            sceneParent.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    xOffset = mouseEvent.getSceneX();
                    yOffset = mouseEvent.getSceneY();
                }
            });

            sceneParent.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    window.setX(mouseEvent.getScreenX() - xOffset);
                    window.setY(mouseEvent.getScreenY() - yOffset);
                }
            });

        } catch (IOException ioException) {
            System.err.println("Unable to load scene " + this.getLocation());
            ioException.printStackTrace();
        }
    }


}
