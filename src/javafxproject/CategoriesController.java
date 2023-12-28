/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import static java.text.NumberFormat.Field.INTEGER;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.projectmonkey.object.mapper.ObjectMapper;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class CategoriesController implements Initializable {

    public static void getIpMovie(long ip) {
        movieId = ip;
    }

    static private long movieId;
    static private VBox temp = null;
    static private Statement statement;
    static private String MovieName;
    static private String MoviePath;

    @FXML
    Button popular;
    @FXML
    Button top;
    @FXML
    Button play;
    @FXML
    Button fav;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void getTopPopular(Event e) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        FutureTask<String> futureTask = new FutureTask<>(new popularCallApi());

        executorService.submit(futureTask);

        String apiResult = null;
        try {
            try {
                apiResult = futureTask.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ExecutionException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("API Result: " + apiResult);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) parser.parse(String.valueOf(apiResult));
        } catch (ParseException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray ReturnedData = (JSONArray) jsonObject.get("results");

//        System.out.println("Response: " + (String) InnerResult.get("original_title"));
        Stage popular = new Stage();

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.HORIZONTAL);
        pane.setPrefWidth(700);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        int index = 0;

        try {
            while (index < ReturnedData.size()) {
                JSONObject InnerResult = (JSONObject) ReturnedData.get(index);
                VBox MovieContentLayout = new VBox();
                MovieContentLayout.setPrefWidth(200);
                MovieContentLayout.setPrefHeight(200);
                MovieContentLayout.setStyle("-fx-border-color:gray;");
                MovieContentLayout.setAlignment(Pos.TOP_CENTER);
                MovieContentLayout.setPadding(new Insets(5, 10, 10, 10));

                Image im = new Image("https://image.tmdb.org/t/p/w500" + InnerResult.get("poster_path"));
                ImageView imageView = new ImageView(im);
                imageView.setFitWidth(190);
                imageView.setFitHeight(140);

                Text nameOFMovie = new Text((String) InnerResult.get("original_title"));

                double wrappingWidth = 180;
                nameOFMovie.setWrappingWidth(wrappingWidth);
                MovieContentLayout.getChildren().add(imageView);
                MovieContentLayout.getChildren().add(nameOFMovie);
                Text idText = new Text("" + InnerResult.get("id"));
                idText.setStyle("-fx-opacity:0.0");
                MovieContentLayout.getChildren().add(idText);
                mouseEvent event = new mouseEvent();
                System.out.println((String) InnerResult.get("original_title") + "//" + (long) InnerResult.get("id"));
                movieId = (long) InnerResult.get("id");
                MovieContentLayout.setOnMouseClicked(event);
                pane.getChildren().add(MovieContentLayout);

                index++;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        } finally {
            ScrollPane scroll = new ScrollPane(pane);
            Scene scene = new Scene(scroll, 700, 600);
            popular.setTitle("Popular Movies");
            popular.setScene(scene);
        }
        popular.show();

    }

    public void getTopRated(Event e) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        FutureTask<String> futureTask = new FutureTask<>(new topRatedCallApi());

        executorService.submit(futureTask);

        String apiResult = null;
        try {
            try {
                apiResult = futureTask.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ExecutionException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("API Result: " + apiResult);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) parser.parse(String.valueOf(apiResult));
        } catch (ParseException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray ReturnedData = (JSONArray) jsonObject.get("results");

        //        System.out.println("Response: " + (String) InnerResult.get("original_title"));
        Stage popular = new Stage();

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.HORIZONTAL);
        pane.setPrefWidth(700);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        int index = 0;

        try {
            while (index < ReturnedData.size()) {
                JSONObject InnerResult = (JSONObject) ReturnedData.get(index);
                VBox MovieContentLayout = new VBox();
                MovieContentLayout.setPrefWidth(200);
                MovieContentLayout.setPrefHeight(200);
                MovieContentLayout.setStyle("-fx-border-color:gray;");
                MovieContentLayout.setAlignment(Pos.TOP_CENTER);
                MovieContentLayout.setPadding(new Insets(5, 10, 10, 10));

                Image im = new Image("https://image.tmdb.org/t/p/w500" + InnerResult.get("poster_path"));
                ImageView imageView = new ImageView(im);
                imageView.setFitWidth(190);
                imageView.setFitHeight(140);

                Text nameOFMovie = new Text((String) InnerResult.get("original_title"));

                double wrappingWidth = 180;
                nameOFMovie.setWrappingWidth(wrappingWidth);
                MovieContentLayout.getChildren().add(imageView);
                MovieContentLayout.getChildren().add(nameOFMovie);
                Text idText = new Text("" + InnerResult.get("id"));
                idText.setStyle("-fx-opacity:0.0");
                MovieContentLayout.getChildren().add(idText);
                mouseEvent event = new mouseEvent();
                System.out.println((String) InnerResult.get("original_title") + "//" + (long) InnerResult.get("id"));
                movieId = (long) InnerResult.get("id");
                MovieContentLayout.setOnMouseClicked(event);
                pane.getChildren().add(MovieContentLayout);

                index++;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        } finally {
            ScrollPane scroll = new ScrollPane(pane);
            Scene scene = new Scene(scroll, 700, 600);
            popular.setTitle("Popular Movies");
            popular.setScene(scene);
        }
        popular.show();
    }

    public void playNOw(Event e) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        FutureTask<String> futureTask = new FutureTask<>(new playNowCallApi());

        executorService.submit(futureTask);

        String apiResult = null;
        try {
            try {
                apiResult = futureTask.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ExecutionException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("API Result: " + apiResult);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) parser.parse(String.valueOf(apiResult));
        } catch (ParseException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray ReturnedData = (JSONArray) jsonObject.get("results");

        //        System.out.println("Response: " + (String) InnerResult.get("original_title"));
        Stage popular = new Stage();

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.HORIZONTAL);
        pane.setPrefWidth(700);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        int index = 0;

        try {
            while (index < ReturnedData.size()) {
                JSONObject InnerResult = (JSONObject) ReturnedData.get(index);
                VBox MovieContentLayout = new VBox();
                MovieContentLayout.setPrefWidth(200);
                MovieContentLayout.setPrefHeight(200);
                MovieContentLayout.setStyle("-fx-border-color:gray;");
                MovieContentLayout.setAlignment(Pos.TOP_CENTER);
                MovieContentLayout.setPadding(new Insets(5, 10, 10, 10));

                Image im = new Image("https://image.tmdb.org/t/p/w500" + InnerResult.get("poster_path"));
                ImageView imageView = new ImageView(im);
                imageView.setFitWidth(190);
                imageView.setFitHeight(140);

                Text nameOFMovie = new Text((String) InnerResult.get("original_title"));

                double wrappingWidth = 180;
                nameOFMovie.setWrappingWidth(wrappingWidth);
                MovieContentLayout.getChildren().add(imageView);
                MovieContentLayout.getChildren().add(nameOFMovie);
                Text idText = new Text("" + InnerResult.get("id"));
                idText.setStyle("-fx-opacity:0.0");
                MovieContentLayout.getChildren().add(idText);
                mouseEvent event = new mouseEvent();
                System.out.println((String) InnerResult.get("original_title") + "//" + (long) InnerResult.get("id"));
                movieId = (long) InnerResult.get("id");
                MovieContentLayout.setOnMouseClicked(event);
                pane.getChildren().add(MovieContentLayout);

                index++;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        } finally {
            ScrollPane scroll = new ScrollPane(pane);
            Scene scene = new Scene(scroll, 700, 600);
            popular.setTitle("Popular Movies");
            popular.setScene(scene);
        }
        popular.show();
    }

    // get Fav page 
    public void getMyFav(Event e) {
        databaseConnection();

        Stage stage3 = new Stage();
        VBox pane = new VBox();
//        pane.setPrefWidth(700);
        pane.setPadding(new Insets(0,50,20,20));
//        pane.setHgap(10);
//        pane.setVgap(10);

        try {
            databaseConnection();
            String sql = "SELECT * FROM mov";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println(resultSet);
            while (resultSet.next()) {
                HBox horiContent = new HBox();
                horiContent.setPadding(new Insets(30,100,0,0));
                Image im = new Image("https://image.tmdb.org/t/p/w500" + resultSet.getString(4));
                ImageView img = new ImageView(im);
                img.setFitWidth(100);
                img.setFitHeight(100);
                horiContent.getChildren().add(img);
                horiContent.getChildren().add(new Text(resultSet.getString(3)));
                pane.getChildren().add(horiContent);
                
            }
            
            System.out.println("Selected");
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ScrollPane scroll = new ScrollPane(pane);
        Scene scene2 = new Scene(scroll,300,300);
        stage3.setScene(scene2);
        stage3.show();
        System.out.println("NotSelected");

    }

    // task of call api data of popular movies
    public static class popularCallApi implements Callable<String> {

        @Override
        public String call() throws Exception {
            URL url = null;
            HttpURLConnection con = null;

            url = new URL("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZDEzMzcyOGM3ODY1MjgzM2NjNjQzNzU0NTc1MmEwOSIsInN1YiI6IjY1N2JmMjUyZTkzZTk1MjE5MDBlMmE1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AKjUevczunPQefu2fcs5ZQem42cyKQX_PjEZ4fi1O8E");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        }

    }

    // task of topRated movies
    public static class topRatedCallApi implements Callable<String> {

        @Override
        public String call() throws Exception {
            URL url = null;
            HttpURLConnection con = null;

            url = new URL("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZDEzMzcyOGM3ODY1MjgzM2NjNjQzNzU0NTc1MmEwOSIsInN1YiI6IjY1N2JmMjUyZTkzZTk1MjE5MDBlMmE1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AKjUevczunPQefu2fcs5ZQem42cyKQX_PjEZ4fi1O8E");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        }

    }

    //task of playingNow Movies
    public static class playNowCallApi implements Callable<String> {

        @Override
        public String call() throws Exception {
            URL url = null;
            HttpURLConnection con = null;

            url = new URL("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZDEzMzcyOGM3ODY1MjgzM2NjNjQzNzU0NTc1MmEwOSIsInN1YiI6IjY1N2JmMjUyZTkzZTk1MjE5MDBlMmE1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AKjUevczunPQefu2fcs5ZQem42cyKQX_PjEZ4fi1O8E");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        }

    }

    // task of getting the details
    public static class detailCallApi implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println(movieId);
            URL url = null;
            HttpURLConnection con = null;

            url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "?language=en-US");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZDEzMzcyOGM3ODY1MjgzM2NjNjQzNzU0NTc1MmEwOSIsInN1YiI6IjY1N2JmMjUyZTkzZTk1MjE5MDBlMmE1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AKjUevczunPQefu2fcs5ZQem42cyKQX_PjEZ4fi1O8E");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        }

    }

    // The Action of Fav clicking button
    public class mouseEvent implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {

            if (t.getSource() instanceof VBox) {
                VBox clickedVBox = (VBox) t.getSource();
                Text label = (Text) clickedVBox.getChildren().get(2);
                movieId = Long.parseLong(label.getText());
            }

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            FutureTask<String> futureTask = new FutureTask<>(new detailCallApi());

            executorService.submit(futureTask);

            String apiDetails = null;
            try {
                try {
                    apiDetails = futureTask.get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ExecutionException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("API Result: " + apiDetails);

            JSONParser parser1 = new JSONParser();
            JSONObject movieDetails = null;
            try {
                movieDetails = (JSONObject) parser1.parse(String.valueOf(apiDetails));
            } catch (ParseException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }

            JSONObject pathOfMovie = (JSONObject) movieDetails.get("belongs_to_collection");
            JSONArray movieCategoriesArray = (JSONArray) movieDetails.get("genres");

//          create a stage a javafx components 
            Stage stage2 = new Stage();
            FlowPane mainContentLayout = new FlowPane();
            mainContentLayout.setPrefWidth(350);
            mainContentLayout.setPrefHeight(500);
            mainContentLayout.setHgap(10);
            mainContentLayout.setPadding(new Insets(20, 80, 20, 20));
            mainContentLayout.setStyle("-fx-background-color:#0766AD");

//            System.out.println(pathOfMovie.get("poster_path"));
            Image detailsPicture = new Image("https://image.tmdb.org/t/p/w500" + pathOfMovie.get("poster_path"));
            MoviePath = (String) pathOfMovie.get("poster_path");
            ImageView imageViewDetails = new ImageView(detailsPicture);
            imageViewDetails.setFitWidth(200);
            imageViewDetails.setFitHeight(200);
            imageViewDetails.setStyle("-fx-border-color:white;");
            mainContentLayout.getChildren().add(imageViewDetails);

            // VBox to hold the data of movie
            VBox content = new VBox();
            // set the name of the moive
            double wrappingWidth = 200;
            String NameOfMOvie = (String) pathOfMovie.get("name");
            String ReplaceNameOfMOvie = NameOfMOvie.replace("Collection", "");
            MovieName = ReplaceNameOfMOvie;
            Font font1 = Font.font("SansSerif", FontWeight.EXTRA_BOLD, 20);
            Text nameOfMovie = new Text(ReplaceNameOfMOvie + "\n");
            nameOfMovie.setFill(Color.WHITE);
            nameOfMovie.setFont(font1);
            content.setAlignment(Pos.TOP_LEFT);
            nameOfMovie.setWrappingWidth(wrappingWidth);
            content.getChildren().add(nameOfMovie);

            // get the collections of the movie
            HBox cat = new HBox();
            int counter = 0;
            String con = "Categroy :  ";
            while (counter < movieCategoriesArray.size()) {
                JSONObject categories = (JSONObject) movieCategoriesArray.get(counter);
                con += (String) categories.get("name") + " - ";
                counter++;
            }
            Text textCateg = new Text(con + "\n");
            textCateg.setFill(Color.WHITE);
            textCateg.setWrappingWidth(wrappingWidth);
            cat.getChildren().add(textCateg);
            Text overviewOFMovie = new Text("OverView : " + (String) (String) movieDetails.get("overview"));
            overviewOFMovie.setWrappingWidth(wrappingWidth);
            overviewOFMovie.setFill(Color.WHITE);

            // fav Button 
            Button fav = new Button("Fav");
            favClieck fav_event = new favClieck();
            fav.setOnMouseClicked(fav_event);
            content.getChildren().add(fav);
            content.getChildren().add(cat);
            content.getChildren().add(overviewOFMovie);

            mainContentLayout.getChildren().add(content);

            Scene scene1 = new Scene(mainContentLayout, 550, 400);
            stage2.setScene(scene1);
            stage2.show();

        }

    }

    // function of database 
    private void databaseConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection con = null;

        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?useSSL=false", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class favClieck implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {
            System.out.println("hello");
            try {
                databaseConnection();
                String sql = "INSERT INTO mov (id_movie,movieName,moviePath) values(" + movieId + ",'" + MovieName + "','" + MoviePath + "')";
                statement.executeUpdate(sql);
                System.out.println("inserted");
            } catch (SQLException ex) {
                Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Notinserted");
        }

    }

    public class mouseEventGetIp implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent t) {

        }

    }
}
