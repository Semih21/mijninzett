package com.mijninzet.projectteamdrie.model.entity.user;

public final class UserSingleton {
    private static UserSingleton singletonInstance;

    // variable of type int
    private int id;
    private int count = 0;

    public int getCount() {
        return count;
    }

    // private constructor restricted to this class itself
    private UserSingleton() {
        this.id = -1;
    }

    // static method to create instance of Singleton class
    public static UserSingleton getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new UserSingleton();
            singletonInstance.count++;
            System.out.println("COUNT IS " + singletonInstance.count);
            // Thread.dumpStack();  // indien je wilt weten welke class deze method als eerste aanroept

        }
        return singletonInstance;
    }

    //getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
