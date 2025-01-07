
package track.pro.tasks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.project.entites.Project;

import track.pro.tasks.entites.Task;
import track.pro.tasks.repository.TaskRepository;
import track.pro.user.entites.User;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public int fillTask(Task task) {
		return taskRepository.fillTask(task);
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.fetchAllTasks();
	}

	@Override
	public Task getTaskById(int taskId) {
		return taskRepository.fetchTaskById(taskId);
	}

	@Override
	public void updateTask(Task task) {
		taskRepository.updateTask(task);
	}

	@Override
	public String getCreatedAt(int userId) {
		return taskRepository.getCreatedAt(userId);
	}
}
