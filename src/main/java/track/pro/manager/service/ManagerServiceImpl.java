package track.pro.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.manager.repository.ManagerRepository;
import track.pro.tasks.entites.Task;


@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepository managerRepository;
	@Override
	public List<Task> getAllTask() {

		return managerRepository.fetchAllTask();
	}
	

}
