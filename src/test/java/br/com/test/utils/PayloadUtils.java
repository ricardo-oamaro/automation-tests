package br.com.test.utils;

public class PayloadUtils {

    public static String createPostPayload(String title, String body, int userId) {
        return String.format("""
            {
                "title": "%s",
                "body": "%s",
                "userId": %d
            }
        """, title, body, userId);
    }
}
