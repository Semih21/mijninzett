package com.mijninzet.projectteamdrie.model.comparator;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.user.User;

import java.util.Comparator;

public class UserNameComparator implements Comparator<User> {


    @Override
    public int compare(User user1, User user2) {
        int primary = user1.getName().compareToIgnoreCase(user2.getName());
        return primary != 0 ? primary
                :user2.getName().compareToIgnoreCase(user1.getName());
    }
}