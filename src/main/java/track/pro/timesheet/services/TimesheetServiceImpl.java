/*
 * 
 * package track.pro.timesheet.services;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import track.pro.tasks.entites.Task; import
 * track.pro.timesheet.entities.Timesheet; import
 * track.pro.timesheet.repository.TimesheetRepository;
 * 
 * 
 * import track.pro.user.entites.User;
 * 
 * @Service public class TimesheetServiceImpl implements TimesheetService {
 * 
 * @Autowired TimesheetRepository timesheetRepository;
 * 
 * @Override public int fillTimesheet(Timesheet timesheet) { return
 * timesheetRepository.fillTimesheet(timesheet) ; }
 * 
 * @Override public List<Task> getAllTasks() { return
 * timesheetRepository.fetchAllTasks(); }
 * 
 * @Override public List<User> getAllUsers() { return
 * timesheetRepository.fetchAllUsers(); }
 * 
 * @Override public int updateStatus(int task_id) { return
 * timesheetRepository.toggleAuthority(task_id); }
 * 
 * @Override public List<Timesheet> getAllTimesheet() {
 * 
 * return timesheetRepository.fetchAllTimesheet(); }
 * 
 * @Override public int deleteTimesheet(int timesheetId) { return
 * timesheetRepository.deleteTimesheet(timesheetId); }
 * 
 * 
 * }
 */

package track.pro.timesheet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.tasks.entites.Task;
import track.pro.timesheet.entities.Timesheet;
import track.pro.timesheet.repository.TimesheetRepository;
import track.pro.user.entites.User;

@Service
public class TimesheetServiceImpl implements TimesheetService {
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public int fillTimesheet(Timesheet timesheet) {
		return timesheetRepository.fillTimesheet(timesheet);
	}

	@Override
	public List<Task> getAllTasks() {
		return timesheetRepository.fetchAllTasks();
	}

	@Override
	public List<User> getAllUsers() {
		return timesheetRepository.fetchAllUsers();
	}

	@Override
	public int updateStatus(int task_id) {
		return timesheetRepository.toggleAuthority(task_id);
	}

	@Override
	public List<Timesheet> getAllTimesheet() {
		return timesheetRepository.fetchAllTimesheet();
	}

	@Override
	public int deleteTimesheet(int timesheetId) {
		return timesheetRepository.deleteTimesheet(timesheetId);
	}

	@Override
	public int getUserIdByUsername(String username) {
		return timesheetRepository.getUserIdByUsername(username);
	}

	@Override
	public List<Task> getTasksByUserId(int userId) {
		return timesheetRepository.getTasksByUserId(userId);
	}

	@Override
	public User getUserById(int userId) {
		return timesheetRepository.getUserById(userId);
	}
}