package track.pro.manager.repository;

import java.util.List;

import track.pro.tasks.entites.Task;
import track.pro.timesheet.entities.Timesheet;

public interface ManagerRepository {
	List<Task> fetchAllTask();
	

}
