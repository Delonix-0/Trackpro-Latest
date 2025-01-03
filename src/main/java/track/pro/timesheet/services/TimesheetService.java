
 
package track.pro.timesheet.services;
import java.util.List;
import track.pro.tasks.entites.Task;
import track.pro.timesheet.entities.Timesheet;
import track.pro.user.entites.User;
public interface TimesheetService {
	int fillTimesheet(Timesheet timesheet);
	List<Task> getAllTasks();
	List<User> getAllUsers();
	int updateStatus(int task_id);
	List<Timesheet> getAllTimesheet();
	int deleteTimesheet(int timesheetId);
	int getUserIdByUsername(String username);
	List<Task> getTasksByUserId(int userId);
	User getUserById(int userId);
}