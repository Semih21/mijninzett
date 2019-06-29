package com.mijninzet.projectteamdrie.model.entity.user;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}































//import com.mijninzet.projectteamdrie.model.entity.WebPage;
//
//public enum Role {
//    DEFAULT("Unknown", null),
//    TEACHER("Teacher", WebPage.getWindowsTeacher()),
//    ADMINISTRATOR("Administrator", WebPage.getWindowsAdministrator()),
//    //eigenaar onderwijseenheid is coordinator
//    COORDINATOR("Coordinator", WebPage.getWindowsCoordinator()),
//    SCHEDULER("Scheduler", WebPage.getWindowsScheduler()),
//    MANAGER("Manager", WebPage.getWindowsManager());
//
//    private String roleDescriptor;
//    private WebPage[] webPages;
//
//    Role(String roleDescriptor, WebPage[] webPages) {
//        this.roleDescriptor = roleDescriptor;
//        this.webPages = webPages;
//    }
//
//    public static Role[] getRolesOfUser() {
//         Role[] roles = {TEACHER, ADMINISTRATOR, COORDINATOR, SCHEDULER, MANAGER};
//        return roles;
//    }
//
//    public static Role getRoleByDescription(String description) {
//        switch (description) {
//            case "Teacher":
//                return TEACHER;
//            case "Administrator":
//                return ADMINISTRATOR;
//            case "COORDINATOR":
//                return COORDINATOR;
//            case "Scheduler":
//                return SCHEDULER;
//            case "Manager":
//                return MANAGER;
//            default:
//                System.err.println("Deze rol bestaat niet");
//                return null;
//        }
//    }
//
//    // getters
//    public String getRoleDescriptor() {
//        return roleDescriptor;
//    }
//
//    public WebPage[] getWebPages() {
//        return webPages;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Role: %s", roleDescriptor);
//    }
//}
//
