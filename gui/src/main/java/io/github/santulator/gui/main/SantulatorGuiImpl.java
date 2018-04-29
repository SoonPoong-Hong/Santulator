package io.github.santulator.gui.main;

import io.github.santulator.gui.common.Placement;
import io.github.santulator.gui.controller.GuiFileHandler;
import io.github.santulator.gui.controller.MainController;
import io.github.santulator.gui.controller.TitleHandler;
import io.github.santulator.gui.model.MainModel;
import io.github.santulator.gui.services.PlacementManager;
import io.github.santulator.gui.view.ViewFxml;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SantulatorGuiImpl implements SantulatorGui {
    private static final Logger LOG = LoggerFactory.getLogger(SantulatorGuiImpl.class);

    private final FXMLLoader mainLoader;

    private final PlacementManager placementManager;

    private final GuiFileHandler guiFileHandler;

    private final MainController mainController;

    private final TitleHandler titleHandler;

    private final MainModel model;

    @Inject
    public SantulatorGuiImpl(final FXMLLoader mainLoader, final PlacementManager placementManager,
        final GuiFileHandler guiFileHandler, final MainController mainController, final TitleHandler titleHandler, final MainModel model) {
        this.mainLoader = mainLoader;
        this.placementManager = placementManager;
        this.guiFileHandler = guiFileHandler;
        this.mainController = mainController;
        this.titleHandler = titleHandler;
        this.model = model;
    }

    @Override
    public void start(final Stage stage) {
        Parent root = ViewFxml.MAIN.loadNode(mainLoader);

        initialise(stage);

        Scene scene = new Scene(root);

        Placement placement = placementManager.getMainWindow();

        stage.setScene(scene);
        stage.setWidth(placement.getWidth());
        stage.setHeight(placement.getHeight());
        if (placement.isPositioned()) {
            stage.setX(placement.getX());
            stage.setY(placement.getY());
        }
        stage.show();
        LOG.info("User interface started");
    }

    private void initialise(final Stage stage) {
        mainController.initialise(stage);
        guiFileHandler.initialise(stage);
        titleHandler.initialise();

        stage.titleProperty().bind(model.titleProperty());
    }
}