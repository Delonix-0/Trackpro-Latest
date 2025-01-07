package track.pro.createtask.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.createtask.repository.CreateTaskRepository;
import track.pro.project.entites.Project;
import track.pro.tasks.entites.Task;
import track.pro.user.entites.User;

@Service
public class CreateTaskServicesImpl implements CreateTaskService {
	@Autowired
	CreateTaskRepository createtaskRepository;

	@Override
	public int addtaskUser(Task task) {

		return createtaskRepository.insertTask(task);
	}

	@Override
	public List<User> getAllUser() {

		return createtaskRepository.fetchAllUser();
	}

	@Override
	public List<Project> getAllProject() {

		return createtaskRepository.fetchAllProject();
	}

	@Override
	public List<User> getAllEmployee() {

		return createtaskRepository.fetchAllEmployee();
	}

	@Override
	public int updateStatus(int taskId) {

		return createtaskRepository.toggleAuthority(taskId);
	}

	@Override
	public int deleteTask(int taskId) {
		return createtaskRepository.deleteTask(taskId);
	}

	@Override
	public List<Project> getProjectsByUserId(int userId) {
		return createtaskRepository.fetchProjectsByUserId(userId);
	}
}
