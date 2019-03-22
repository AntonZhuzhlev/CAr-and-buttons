package application;
	
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public void start(Stage theStage) 
    {
        theStage.setTitle( "Anime" );
 
        // Готовим корневой элемент и сцену для окна
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
 
        // Указываем размер канвы и добавляем ее в корневой элемент
        Canvas canvas = new Canvas( 900, 783 );
        root.getChildren().add( canvas );
 
        // Создаем список с кодами нажатых клавиш
        ArrayList<String> list = new ArrayList<String>();
 
        // Обработка события нажатия на клавишу
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                	// код клавиши
                    String code = e.getCode().toString(); 
                    if ( !list.contains(code) )
                        list.add( code );
                }
            });
 
        // обработка "отпускания" клавиши
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    list.remove( code );
                }
            });
 
        GraphicsContext gc = canvas.getGraphicsContext2D();
 
        // Создаем объекты изображений
        
        Image Car = new Image( "Car.png" );
        Image Highway = new Image( "Highway.png" );
        final long startTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	double t = (currentNanoTime - startTime)/10000000.0;
				while (t > 450) {
					t = t - 450;
				}
				double x = t;
                // Каждый раз очищаем канву
				gc.clearRect(0, 0, 900,783);
				gc.drawImage(Highway,-x,0);
				gc.drawImage(Highway,450-x,0);
				gc.drawImage(Highway,900-x,0);
				gc.drawImage(Highway,1350-x,0);
				gc.drawImage(Highway,-x,261);
				gc.drawImage(Highway,450-x,261);
				gc.drawImage(Highway,900-x,261);
				gc.drawImage(Highway,1350-x,261);
				gc.drawImage(Highway,-x,522);
				gc.drawImage(Highway,450-x,522);
				gc.drawImage(Highway,900-x,522);
				gc.drawImage(Highway,1350-x,522);
                
                // Меняем изображение, в зависимости от кода клавиши
               /* if (input.contains("LEFT"))
                    gc.drawImage( Car, 0, 0 );
                else
                    gc.drawImage( Car, 0, 261 );
 
                if (input.contains("RIGHT"))
                    gc.drawImage( Car, 0, 522 );
                else
                    gc.drawImage( Car, 0, 261 );*/
                if (list.contains("UP")) {
                	gc.drawImage( Car, 260, 0 );
                } else if (list.contains("DOWN")) {
                	 gc.drawImage( Car, 260, 522 );
                } else if (list.contains("RIGHT")) {
                	gc.drawImage( Car, 520, 261 );
                } else if (list.contains("LEFT")) {
                	gc.drawImage( Car, 0, 261 );
                } else {
                	gc.drawImage( Car, 260, 261 );
                }
            }
        }.start();
 
        theStage.show();
    }
	public static void main(String[] args)
    {
        launch(args);
    }

}
