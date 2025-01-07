package track.pro.leaves.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import track.pro.leaves.entites.Leaves;
import track.pro.leaves.entites.LeaveBalance;
import track.pro.leaves.repository.LeavesRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LeavesServiceImpl implements LeavesService {

	@Autowired
	private LeavesRepository leavesRepository;

	@Override
	public LeaveBalance getLeaveBalance(int user_id) {
		return leavesRepository.fetchLeaveBalanceByUser(user_id);
	}

	@Override
	public int calculateLeaveDays(Date startDate, Date endDate) {
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
		return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
	}

	@Override
	public int submitLeaveRequest(Leaves leaves) {
		return leavesRepository.insertLeaveRequest(leaves);
	}

	@Override
	public List<Leaves> fetchAllLeaveRequests() {
		return leavesRepository.getAllLeaveRequests();
	}

	@Override
	public int updateStatus(int leaveId) {
		int result = leavesRepository.toggleAuthority(leaveId);
		if (result > 0) {
			updateRemainingLeaves(leaveId);
		}
		return result;
	}

	@Override
	public void updateRemainingLeaves(int leaveId) {
		int leaveDays = leavesRepository.getLeaveDays(leaveId);
		Leaves leave = leavesRepository.getLeaveById(leaveId);
		if (leave.isStatus()) {
			LeaveBalance leaveBalance = leavesRepository.fetchLeaveBalanceByUser(leave.getUser_id());
			int updatedBalance = leaveBalance.getRemaining_leaves() - leaveDays;
			leavesRepository.updateRemainingLeaves(leave.getUser_id(), updatedBalance);
		}
	}

	@Override
	public List<Leaves> fetchAllLeaveRequestsByUserId(int user_id) {
		return leavesRepository.getAllLeaveRequestsByUserId(user_id);
	}
}