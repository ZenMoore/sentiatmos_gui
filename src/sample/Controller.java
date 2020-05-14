package sample;

import javafx.fxml.FXML;
import java.io.*;
import java.sql.Time;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea textarea;

    @FXML
    private Label label;

    private int pos = 0;

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
            File file = new File("./senti.txt");
            while(true){
                if(file.exists()){
                    System.out.println("file exists.");
                    FileInputStream in = new FileInputStream(file);
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    char sentinum = (char)in.read();
                    System.out.println(sentinum);
                    String sentiment = "";
//                    System.out.println(sentiment);
                    switch (sentinum){
                        case '0':sentiment += "不满";break;
                        case '1':sentiment += "低落";break;
                        case '2':sentiment += "愤怒";break;
                        case '3':sentiment += "开心";break;
                        case '4':sentiment += "喜悦";break;
                        default:sentiment += "厌恶";break;
                    }

                    this.label.setText(sentiment);
//                    reader.close();
                    in.close();
                    Thread.sleep(1000);
                    file.delete();
                    break;
                }
            }
//            Thread.sleep(15000);
//            if(pos == 0){
//                label.setText("喜悦");
//                pos++;
//            }else{
//                label.setText("低落");
//            }

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
