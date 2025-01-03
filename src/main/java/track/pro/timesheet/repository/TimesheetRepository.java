/*
 * 
 * package track.pro.timesheet.repository; import java.util.List;
 * 
 * import track.pro.tasks.entites.Task; import
 * track.pro.timesheet.entities.Timesheet; import track.pro.user.entites.User;
 * 
 * 
 * public interface TimesheetRepository { int fillTimesheet(Timesheet
 * timesheet); List<Task> fetchAllTasks(); List<User> fetchAllUsers(); int
 * toggleAuthority(int task_id); List<Timesheet> fetchAllTimesheet();
 * 
 * int deleteTimesheet(int timesheetId); }
 */

package track.pro.timesheet.repository;

import java.util.List;

import track.pro.tasks.entites.Task;
import track.pro.timesheet.entities.Timesheet;
import track.pro.user.entites.User;

public interface TimesheetRepository {
    int fillTimesheet(Timesheet timesheet);
    List<Task> fetchAllTasks();
    List<User> fetchAllUsers();
    int toggleAuthority(int task_id);
    List<Timesheet> fetchAllTimesheet();
    int deleteTimesheet(int timesheetId);
    int getUserIdByUsername(String username);
    List<Task> getTasksByUserId(int userId);
    User getUserById(int userId);
}