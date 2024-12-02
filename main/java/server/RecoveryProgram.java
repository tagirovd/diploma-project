package server;

import java.sql.SQLException;

public class RecoveryProgram {

    public void RecoveryProgram(String age, String height, String weight, String gender,
                           String hand, String leg, String spinal, String head_and_neck, String other,
                           String r_bench_press, String r_deadlift, String r_squats,
                           String w_bench_press, String w_deadlift, String w_squats) throws ClassNotFoundException, SQLException {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String RM_bench_press = String.valueOf(Integer.parseInt(w_bench_press) * 36/(37 - Integer.parseInt(r_bench_press)));
        String RM_deadlift = String.valueOf(Integer.parseInt(w_deadlift) * 36/(37 - Integer.parseInt(r_deadlift)));
        String RM_squats = String.valueOf(Integer.parseInt(w_squats) * 36/(37 - Integer.parseInt(r_squats)));

        String chest_biceps_exercise_1 = databaseHandler.getInjuryNameBySubcategory(spinal, "Грудь1");
        String chest_biceps_exercise_2 = databaseHandler.getInjuryNameBySubcategory(spinal, "Грудь2");
        String chest_biceps_exercise_3 = databaseHandler.getInjuryNameBySubcategory(spinal, "Грудь3");
        String chest_biceps_exercise_4 = databaseHandler.getInjuryNameBySubcategory(hand, "Бицепс1");
        String chest_biceps_exercise_5 = databaseHandler.getInjuryNameBySubcategory(hand, "Бицепс2");
        String chest_biceps_exercise_6 = databaseHandler.getInjuryNameBySubcategory(hand, "Бицепс3");

        String chest_biceps_weight_1 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(spinal, "Грудь1")) / 100 * Double.parseDouble(RM_bench_press));
        String chest_biceps_weight_2 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(spinal, "Грудь2")) / 100 * Double.parseDouble(RM_bench_press));
        String chest_biceps_weight_3 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(spinal, "Грудь3")) / 100 * Double.parseDouble(RM_bench_press));
        String chest_biceps_weight_4 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Бицепс1")) / 100 * Double.parseDouble(RM_bench_press));
        String chest_biceps_weight_5 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Бицепс2")) / 100 * Double.parseDouble(RM_bench_press));
        String chest_biceps_weight_6 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Бицепс3")) / 100 * Double.parseDouble(RM_bench_press));

        databaseHandler.setTraining(chest_biceps_exercise_1,  "Грудь1", spinal, chest_biceps_weight_1);
        databaseHandler.setTraining(chest_biceps_exercise_2,  "Грудь2", spinal, chest_biceps_weight_2);
        databaseHandler.setTraining(chest_biceps_exercise_3,  "Грудь3", spinal, chest_biceps_weight_3);
        databaseHandler.setTraining(chest_biceps_exercise_4,  "Бицепс1", hand, chest_biceps_weight_4);
        databaseHandler.setTraining(chest_biceps_exercise_5,  "Бицепс2", hand, chest_biceps_weight_5);
        databaseHandler.setTraining(chest_biceps_exercise_6,  "Бицепс3", hand, chest_biceps_weight_6);



        String back_triceps_exercise_1 = databaseHandler.getInjuryNameBySubcategory(spinal, "Спина1");
        String back_triceps_exercise_2 = databaseHandler.getInjuryNameBySubcategory(spinal, "Спина2");
        String back_triceps_exercise_3 = databaseHandler.getInjuryNameBySubcategory(spinal, "Спина3");
        String back_triceps_exercise_4 = databaseHandler.getInjuryNameBySubcategory(hand, "Трицепс1");
        String back_triceps_exercise_5 = databaseHandler.getInjuryNameBySubcategory(hand, "Трицепс2");
        String back_triceps_exercise_6 = databaseHandler.getInjuryNameBySubcategory(hand, "Трицепс3");

        String back_triceps_weight_1 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(spinal, "Спина1")) / 100 * Double.parseDouble(RM_bench_press));
        String back_triceps_weight_2 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(spinal, "Спина2")) / 100 * Double.parseDouble(RM_bench_press));
        String back_triceps_weight_3 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(spinal, "Спина3")) / 100 * Double.parseDouble(RM_bench_press));
        String back_triceps_weight_4 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Трицепс1")) / 100 * Double.parseDouble(RM_bench_press));
        String back_triceps_weight_5 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Трицепс2")) / 100 * Double.parseDouble(RM_bench_press));
        String back_triceps_weight_6 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Трицепс3")) / 100 * Double.parseDouble(RM_bench_press));

        databaseHandler.setTraining(back_triceps_exercise_1,  "Спина1", spinal, back_triceps_weight_1);
        databaseHandler.setTraining(back_triceps_exercise_2,  "Спина2", spinal, back_triceps_weight_2);
        databaseHandler.setTraining(back_triceps_exercise_3,  "Спина3", spinal, back_triceps_weight_3);
        databaseHandler.setTraining(back_triceps_exercise_4,  "Трицепс1", hand, back_triceps_weight_4);
        databaseHandler.setTraining(back_triceps_exercise_5,  "Трицепс2", hand, back_triceps_weight_5);
        databaseHandler.setTraining(back_triceps_exercise_6,  "Трицепс3", hand, back_triceps_weight_6);



        String legs_shoulders_exercise_1 = databaseHandler.getInjuryNameBySubcategory(leg, "Ноги1");
        String legs_shoulders_exercise_2 = databaseHandler.getInjuryNameBySubcategory(leg, "Ноги2");
        String legs_shoulders_exercise_3 = databaseHandler.getInjuryNameBySubcategory(leg, "Ноги3");
        String legs_shoulders_exercise_4 = databaseHandler.getInjuryNameBySubcategory(hand, "Плечи1");
        String legs_shoulders_exercise_5 = databaseHandler.getInjuryNameBySubcategory(hand, "Плечи2");
        String legs_shoulders_exercise_6 = databaseHandler.getInjuryNameBySubcategory(hand, "Плечи3");

        String legs_shoulders_weight_1 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(leg, "Ноги1")) / 100 * Double.parseDouble(RM_squats));
        String legs_shoulders_weight_2 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(leg, "Ноги2")) / 100 * Double.parseDouble(RM_squats));
        String legs_shoulders_weight_3 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(leg, "Ноги3")) / 100 * Double.parseDouble(RM_squats));
        String legs_shoulders_weight_4 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Плечи1")) / 100 * Double.parseDouble(RM_squats));
        String legs_shoulders_weight_5 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Плечи2")) / 100 * Double.parseDouble(RM_squats));
        String legs_shoulders_weight_6 = String.valueOf(Double.parseDouble(databaseHandler.getPercentage(hand, "Плечи3")) / 100 * Double.parseDouble(RM_squats));

        databaseHandler.setTraining(legs_shoulders_exercise_1,  "Ноги1", leg, legs_shoulders_weight_1);
        databaseHandler.setTraining(legs_shoulders_exercise_2,  "Ноги2", leg, legs_shoulders_weight_2);
        databaseHandler.setTraining(legs_shoulders_exercise_3,  "Ноги3", leg, legs_shoulders_weight_3);
        databaseHandler.setTraining(legs_shoulders_exercise_4,  "Плечи1", hand, legs_shoulders_weight_4);
        databaseHandler.setTraining(legs_shoulders_exercise_5,  "Плечи2", hand, legs_shoulders_weight_5);
        databaseHandler.setTraining(legs_shoulders_exercise_6,  "Плечи3", hand, legs_shoulders_weight_6);
    }
}
