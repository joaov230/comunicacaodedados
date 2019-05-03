/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigosdelinha;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author joaov230
 */
public class Tela extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Conversor
        Conversor con = new Conversor();
        
        
        // Texto
        TextField tf = new TextField();
        tf.setMaxWidth(600);


        // Grafico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();       
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-2);
        yAxis.setUpperBound(2);
        yAxis.setTickUnit(1);
 
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().add(series);
        
               
        // Botões
        Button btnNRZL = new Button();
        btnNRZL.setText("NRZ-L");
        btnNRZL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Muda o gráfico pra nrzl
                con.nrzlCodificado(tf.getText());
                
                series.getData().clear();
                
                
                for (int i = 1; i < con.sinal.size(); i++) {
                    try {
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i)));
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i+1)));
                        
                    } catch (Exception e) {
                        
                    }
                }
                
                // Seta a string do textfield
                //series.setName(tf.getText());
            }
        });

        Button btnNRZI = new Button();        
        btnNRZI.setText("NRZ-I");
        btnNRZI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Muda o gráfico pra nrzi
                con.nrziCodificado(tf.getText());
                
                series.getData().clear();
                
                for (int i = 1; i < con.sinal.size(); i++) {
                    try {
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i)));
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i+1)));
                        
                    } catch (Exception e) {
                        
                    }
                }
                
                
                // Seta a string do textfield
                //series.setName();
            }
        });
        
        Button btnAMI = new Button();
        btnAMI.setText("AMI");
        btnAMI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Muda o gráfico pra AMI
                con.bamiCodificado(tf.getText());
                
                series.getData().clear();
                
                for (int i = 1; i < con.sinal.size(); i++) {
                    try {
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i)));
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i+1)));
                        
                    } catch (Exception e) {
                        
                    }
                }
                
                // Seta a string do textfield
                //series.setName();
            }
        });
        
        Button btnPT = new Button();
        btnPT.setText("PSEUDOTERNÁRIO");
        btnPT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Muda o gráfico pra pseudoternário
                con.pseudoternarioCodificado(tf.getText());
                
                series.getData().clear();
                
                for (int i = 1; i < con.sinal.size(); i++) {
                    try {
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i)));
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i+1)));
                        
                    } catch (Exception e) {
                        
                    }
                }
                
                // Seta a string do textfield
                //series.setName();
            }
        });
        
        Button btnMAN = new Button();
        btnMAN.setText("MANCHESTER");
        btnMAN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Muda o gráfico pra manchester
                con.manchesterCodificado(tf.getText());
                
                series.getData().clear();
                
               
                for (int i = 2; i < con.sinal.size(); i++) {
                    try {
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i)));
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i+1)));
                    } catch (Exception e) {
                        
                    }
                }
                
                // Seta a string do textfield
                //series.setName();
            }
        });

        Button btnMANDIF = new Button();
        btnMANDIF.setText("MANCHESTER DIFERENCIAL");
        btnMANDIF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Muda o gráfico pra manchester diferencial
                con.manchesterDiferencialCodificado(tf.getText());
                
                series.getData().clear();
                
                for (int i = 1; i < con.sinal.size(); i++) {
                    try {
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i)));
                        series.getData().add(new XYChart.Data("" + i, con.sinal.get(i+1)));
                        
                    } catch (Exception e) {
                        
                    }
                }
                
            }
        });

        Button btnCLEAR = new Button();
        btnCLEAR.setText("CLEAR");
        btnCLEAR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                series.getData().clear();
            }
        });
        
        
        
        // Adiciona no VBox
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(2);
        hb.getChildren().addAll(btnNRZL, btnNRZI, btnAMI, btnPT, btnMAN, btnMANDIF);
        
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(tf, hb, btnCLEAR, lineChart);
        
        
        Scene scene = new Scene(vb, 700, 600);
        
        primaryStage.setTitle("Codigos de Linha");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
