package sample;

import javafx.fxml.FXML;
import java.io.*;
import java.sql.Time;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea textarea;

    public void get_text() throws IOException{
//        return textarea.getText();
        String path = "D:\\Python\\Workpalce\\Text_SentiAtmos\\text\\article.txt";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write 解决中文乱码问题
        // FileWriter fw = new FileWriter(file, true);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textarea.getText());
        bw.flush();
        bw.close();
        fw.close();
    }

    public void launch_mess() throws IOException{
        String path = "D:\\Python\\Workpalce\\Text_SentiAtmos\\text\\o.temp";
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write 解决中文乱码问题
        // FileWriter fw = new FileWriter(file, true);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("");
        bw.flush();
        bw.close();
        fw.close();
    }

    public void delete_mess() throws InterruptedException {
        Thread.sleep(1000);
        String path = "D:\\Python\\Workpalce\\Text_SentiAtmos\\text\\o.temp";
        File file = new File(path);
        file.delete();
    }

    public void run(){
        System.out.println("running");
        try{
            this.get_text();
            this.launch_mess();
            this.delete_mess();
        }catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("IO Exception");
            alert.setContentText(ex.getMessage());
        }catch (InterruptedException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Interrupted Exception");
            alert.setContentText(ex.getMessage());
        }


    }
}
