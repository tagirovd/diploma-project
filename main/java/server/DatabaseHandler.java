package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public ResultSet getLoginUser(String password, String name) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USER_NAME + "=? AND " + Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, name);
            prSt.setString(2, password);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {e.printStackTrace();}

        return resSet;
    }
    public static boolean getLoginNewUser(String password, String name) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getLoginUser(password, name);

        int counter = 0;
        try {while(result.next()) counter++;}
        catch (SQLException e) {e. printStackTrace();}

        if(counter >= 1) return true;
        else return false;
    }
    public void registrationUser(String password, String name, String email, String age, String height, String weight, String gender) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + ","
                + Const.USER_PASSWORD + "," + Const.USER_EMAIL + "," + Const.USER_AGE + ","
                + Const.USER_HEIGHT + "," + Const.USER_WEIGHT + "," + Const.USER_GENDER + ")"
                + "VALUES(?,?,?,?,?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, name);
        prSt.setString(2, password);
        prSt.setString(3, email);
        prSt.setString(4, age);
        prSt.setString(5, height);
        prSt.setString(6, weight);
        prSt.setString(7, gender);
        try {prSt.executeUpdate();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public void registrationInjury(String hand, String leg, String spinal, String head_and_neck, String other) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.INJURY_TABLE + "(" + Const.INJURY_HAND + ","
                + Const.INJURY_LEG + "," + Const.INJURY_SPINAL + "," + Const.INJURY_HEAD_AND_NECK + ","
                + Const.INJURY_OTHER + ")" + "VALUES(?,?,?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, hand);
        prSt.setString(2, leg);
        prSt.setString(3, spinal);
        prSt.setString(4, head_and_neck);
        prSt.setString(5, other);
        try {prSt.executeUpdate();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public void registrationLevel(String bench_press, String deadlift, String squats, String w_bench_press, String w_deadlift, String w_squats) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.LEVEL_TABLE + "(" + Const.LEVEL_R_BENCH_PRESS + ","
                + Const.LEVEL_R_DEADLIFT + "," + Const.LEVEL_R_SQUATS + "," + Const.LEVEL_W_BENCH_PRESS + ","
                + Const.LEVEL_W_DEADLIFT + "," + Const.LEVEL_W_SQUATS + ")" + "VALUES(?,?,?,?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, bench_press);
        prSt.setString(2, deadlift);
        prSt.setString(3, squats);
        prSt.setString(4, w_bench_press);
        prSt.setString(5, w_deadlift);
        prSt.setString(6, w_squats);
        try {prSt.executeUpdate();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public String getInjuryNameBySubcategory(String injury, String category) throws ClassNotFoundException {
        String name = null;
        String select = "SELECT " + Const.EXERCISE_NAME + " FROM " + Const.EXERCISE_TABLE + " WHERE " + Const.EXERCISE_SUBCATEGORY + " =? AND " + Const.EXERCISE_CATEGORY + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, injury);
            prSt.setString(2, category);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString(Const.EXERCISE_NAME);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getPercentage(String injury, String category) throws ClassNotFoundException {
        String name = null;
        String select = "SELECT " + Const.EXERCISE_PERCENTAGE + " FROM " + Const.EXERCISE_TABLE + " WHERE " + Const.EXERCISE_SUBCATEGORY + " =? AND " + Const.EXERCISE_CATEGORY + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, injury);
            prSt.setString(2, category);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString(Const.EXERCISE_PERCENTAGE);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public void setTraining(String exercise, String category, String subcategory, String weight) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.TRAINING_TABLE + "(" + Const.TRAINING_NAME + ","
                + Const.TRAINING_CATEGORY + "," + Const.TRAINING_SUBCATEGORY + "," + Const.TRAINING_WEIGHT + ")"
                + "VALUES(?,?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, exercise);
        prSt.setString(2, category);
        prSt.setString(3, subcategory);
        prSt.setString(4, weight);

        try {prSt.executeUpdate();}
        catch (SQLException e) {throw new RuntimeException(e);}
    }
    public String getTrainingExercise(String category, String subcategory) throws ClassNotFoundException {
        String name = null;
        String select = "SELECT " + Const.TRAINING_NAME + " FROM " + Const.TRAINING_TABLE + " WHERE " + Const.TRAINING_CATEGORY + "=? AND " + Const.TRAINING_SUBCATEGORY + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, category);
            prSt.setString(2, subcategory);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString(Const.TRAINING_NAME);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getTrainingWeight(String category) throws ClassNotFoundException {
        String name = null;
        String select = "SELECT " + Const.TRAINING_WEIGHT + " FROM " + Const.TRAINING_TABLE + " WHERE " + Const.TRAINING_CATEGORY + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, category);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                name = resultSet.getString(Const.TRAINING_WEIGHT);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getUserWeight(String name, String password) throws ClassNotFoundException {
        String a = null;
        String select = "SELECT " + Const.USER_WEIGHT + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, name);
            prSt.setString(2, password);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                a = resultSet.getString(Const.USER_WEIGHT);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    public String getUserHeight(String name, String password) throws ClassNotFoundException {
        String a = null;
        String select = "SELECT " + Const.USER_HEIGHT + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, name);
            prSt.setString(2, password);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                a = resultSet.getString(Const.USER_HEIGHT);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    public String getUserAge(String name, String password) throws ClassNotFoundException {
        String a = null;
        String select = "SELECT " + Const.USER_AGE + " FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, name);
            prSt.setString(2, password);

            ResultSet resultSet = prSt.executeQuery();

            if (resultSet.next()) {
                a = resultSet.getString(Const.USER_AGE);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}
