package edu.cuny.brooklyn.cisc3120.gui.javafxshapes;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFXShapesApp extends Application
{
    private static final int RECT_TOP_LEFT_X_POSITION = 0;
    private static final int RECT_TOP_LEFT_Y_POSITION = 0;
    private static final int RECT_WIDTH = 200;
    private static final int RECT_HEIGHT = 200;
    private static final int RECT_SCENE_WIDTH = 400;
    private static final int RECT_SCENE_HEIGHT = 400;
    private static final int CIRCLE_CENTER_X = 200;
    private static final int CIRCLE_CENTER_Y = 200;
    private static final int CIRCLE_RADIUS = 200;
    private static final int CIRCLE_SCENE_WIDTH = 400;
    private static final int CIRCLE_SCENE_HEIGHT = 400;

    boolean hasOldX = false;
    double xOld;
    boolean movingRight = false;  
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group rectGroup = new Group();
        Rectangle rectangle = new Rectangle(
                RECT_TOP_LEFT_X_POSITION, 
                RECT_TOP_LEFT_Y_POSITION, 
                RECT_WIDTH, 
                RECT_HEIGHT);
        rectangle.setFill(Color.RED);
        rectGroup.getChildren().add(rectangle);
        Scene rectScene = new Scene(rectGroup, RECT_SCENE_WIDTH, RECT_SCENE_HEIGHT);

        Circle circle = new Circle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, CIRCLE_RADIUS);
        circle.setFill(Color.GREEN);
        Group circGroup = new Group();
        circGroup.getChildren().add(circle);
        Scene circScene = new Scene(circGroup, CIRCLE_SCENE_WIDTH, CIRCLE_SCENE_HEIGHT);
        
      
        
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (movingRight) {
                    System.out.println("Mouse entered from left");
                } else {
                    System.out.println("Mouse entered from right");
                }
                primaryStage.setScene(circScene);
                primaryStage.setTitle("Circle");
                primaryStage.show();
            }
        });
        
        rectangle.setOnMouseMoved(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (!hasOldX) {
                            xOld = event.getX();
                            hasOldX = true;
                        } else {
                            double xNew = event.getX();
                            if (xNew > xOld) {
                                movingRight = true;
                            } else {
                                movingRight = false;
                            }
                        }
                    }
                }
        );

        circle.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(rectScene);
                primaryStage.setTitle("Rectangle");
                primaryStage.show();                                
            }
        });
        primaryStage.setScene(rectScene);
        primaryStage.setTitle("Rectangle");
        primaryStage.show();    
        System.out.println("after the primary stage is shown");
        
        primaryStage.setScene(circScene);
        primaryStage.setTitle("Circle");
        primaryStage.show();        
        
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Secondary Stage");
        secondaryStage.setScene(rectScene);
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        // try also and observe the difference
        //     secondaryStage.show(); 
        secondaryStage.showAndWait();
        System.out.println("after the secondary stage is shown");
    }
    
    public static void main( String[] args )
    {
        launch(args);
    }    
}
