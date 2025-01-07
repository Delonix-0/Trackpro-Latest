/*
 * package track.pro.leaves.services;
 * 
 * import track.pro.leaves.entites.Leaves; import
 * track.pro.leaves.entites.LeaveBalance;
 * 
 * import java.util.Date; import java.util.List;
 * 
 * public interface LeavesService { LeaveBalance getLeaveBalance(int user_id);
 * 
 * int calculateLeaveDays(Date startDate, Date endDate);
 * 
 * int submitLeaveRequest(Leaves leave); List<Leaves> fetchAllLeaveRequests();
 * int updateStatus(int leaveId);
 * 
 * 
 * }
 */


package track.pro.leaves.services;

import track.pro.leaves.entites.Leaves;
import track.pro.leaves.entites.LeaveBalance;

import java.util.Date;
import java.util.List;

public interface LeavesService {
    LeaveBalance getLeaveBalance(int user_id);

    int calculateLeaveDays(Date startDate, Date endDate);

    int submitLeaveRequest(Leaves leave);

    List<Leaves> fetchAllLeaveRequests();

    int updateStatus(int leaveId);

    void updateRemainingLeaves(int leaveId); // New method to update remaining leaves

    List<Leaves> fetchAllLeaveRequestsByUserId(int user_id);
}
