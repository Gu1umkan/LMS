package lms;

public class MyGenareteId {
    public static long idForGroup = 1;
    public static long idForStudent = 1;
    public static long idForLesson = 1;

    public static long generateIdForGroup() {
        return idForGroup++;
    }

    public static long generateIdForStudent() {
        return idForStudent++;
    }

    public static long generateIdForLesson() {
        return idForLesson++;
    }
}
