package com.tsv.util;

import com.tsv.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Utils {

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null && (id.length() > 0) && id.matches("[+]?\\d+");
    }

    public static boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }

    public static User createStubUser(final int id,
                                      final String name,
                                      final int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    public static boolean idIsValid(final String id, Map<Integer, User> repo){
        return id != null &&  id.matches("[+]?\\d+") && repo.get(Integer.parseInt(id)) != null;
    }

    public static void checkInitParam(final Object users){

    };
}
