package track.pro.leave.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.leave.entities.Leave;
import track.pro.leave.repository.LeaveRepository;
import track.pro.leave.repository.LeaveRepositoryImpl;

@Service
public class LeaveServiceImpl implements LeaveService{
	
	
	@Autowired
	LeaveRepositoryImpl leaveRepositoryimpl;

	@Override
	public int applyLeave(Leave leave) {
		return leaveRepositoryimpl.applyLeave(leave) ;
	}

}
