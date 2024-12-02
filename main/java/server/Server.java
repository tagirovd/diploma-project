package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        String hand = null, leg = null, spinal = null, head_and_neck = null, other = null;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                DatabaseHandler dbHandler = new DatabaseHandler();

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String input = in.readLine();
                    String[] tokens = input.split(" ");

                    switch (tokens[0]) {
                        case "REGISTER_USER_INJURY_LEVEL" -> {
                            String name = tokens[1];
                            String password = tokens[2];
                            String email = tokens[3];
                            String age = tokens[4];
                            String height = tokens[5];
                            String weight = tokens[6];
                            String gender = tokens[7];
                            dbHandler.registrationUser(name, password, email, age, height, weight, gender);
                            out.println("USER_REGISTERED");

                            hand = tokens[8];
                            leg = tokens[9];
                            spinal = tokens[10];
                            head_and_neck = tokens[11];
                            other = tokens[12];
                            dbHandler.registrationInjury(hand, leg, spinal, head_and_neck, other);
                            out.println("REGISTERED_INJURY");

                            String r_bench_press = tokens[13];
                            String r_deadlift = tokens[14];
                            String r_squats = tokens[15];
                            String w_bench_press = tokens[16];
                            String w_deadlift = tokens[17];
                            String w_squats = tokens[18];
                            dbHandler.registrationLevel(r_bench_press, r_deadlift, r_squats, w_bench_press, w_deadlift, w_squats);
                            out.println("REGISTERED_LEVEL");

                            RecoveryProgram recoveryProgram = new RecoveryProgram();
                            recoveryProgram.RecoveryProgram(age, height, weight, gender, hand, leg, spinal, head_and_neck,
                                    other, r_bench_press, r_deadlift, r_squats, w_bench_press, w_deadlift, w_squats);
                        }
                        case "SUBCATEGORY_USE" -> {
                            out.println("Нет");
                        }
                        case "LOGIN_USER" -> {
                            String password = tokens[1];
                            String name = tokens[2];
                            out.println(DatabaseHandler.getLoginNewUser(password, name));
                        }

                        default -> out.println("INVALID_REQUEST");
                    }
                } catch (IOException e) {e.printStackTrace();}
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
