package sample;

import javafx.event.EventHandler;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Box box = new Box();//рисуем кубическую фигуру
        //указываем параметры
        box.setWidth(150.0);
        box.setHeight(150.0);
        box.setDepth(100.0);

        //позиционируем фигуру
        box.setTranslateX(350);
        box.setTranslateY(150);
        box.setTranslateZ(50);

        //Настраиваем текстовый объект

        Text text = new Text("Напишите любую букву для врашения фигуры, нажмите на фигуру, чтобы остановить вращение");
        text.setFont(Font.font(null, FontWeight.BOLD, 15));

        text.setFill(Color.CORNFLOWERBLUE);

        text.setX(20);
        text.setY(50);

        //настройка материала фигуры
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKGOLDENROD);

        //добавление диффузного цвета на фигуру
        box.setMaterial(material);

        //устанавливаем вращение на фигуру
        RotateTransition rT = new RotateTransition();

        //устанавливаем параметр вращения
        rT.setDuration(Duration.millis(10000));

        //устанавливаем узел для вращения
        rT.setNode(box);

        // настраиваем оси вращения
        rT.setAxis(Rotate.Y_AXIS);

        //устанавливаем угол вращения
        rT.setByAngle(360);

        //задаем цикл количества вращений
        rT.setCycleCount(10);

        //autoreverse устанавливаем как false
        rT.setAutoReverse(false);

        //устанавливаем текстовое поле
        TextField tF = new TextField();

        //позиционируем текстовое поле
        tF.setLayoutX(50);
        tF.setLayoutY(100);

        //устанавливаем событие нажатия кнопки
        EventHandler<KeyEvent> eHTF = new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){

                //Проиграть анимацию
                rT.play();
            }
        };

        //добавляем событие на текстовое поле
        tF.addEventHandler(KeyEvent.KEY_TYPED, eHTF);

        //назначение клик мыши на фигуру
        EventHandler<MouseEvent> eHB =
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        rT.stop();
                    }
                };
        //добавление назначения события на фигуру
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, eHB);

        //создание группового объекта
        Group root = new Group(box, tF, text);

        //создание объекта сцены
        Scene scene = new Scene(root, 600,300);

        //настройка перспективы камеры

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        scene.setCamera(camera);

        //Назначение подписи
        primaryStage.setTitle("Event Handler + 3D animation exercise");

        //добавление сцены на площадку
        primaryStage.setScene(scene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
