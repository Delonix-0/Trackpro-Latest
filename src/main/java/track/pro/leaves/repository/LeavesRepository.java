package track.pro.leaves.repository;

import track.pro.leaves.entites.Leaves;
import track.pro.leaves.entites.LeaveBalance;

import java.util.List;

public interface LeavesRepository {
    LeaveBalance fetchLeaveBalanceByUser(int user_id);

    void updateRemainingLeaves(int user_id, int updatedBalance);

    int insertLeaveRequest(Leaves leaves);

    List<Leaves> getAllLeaveRequests();

    int toggleAuthority(int leaveId);

    int getLeaveDays(int leaveId); // New method to fetch leave days
    Leaves getLeaveById(int leaveId);
}