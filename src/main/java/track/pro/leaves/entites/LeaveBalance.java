package track.pro.leaves.entites;

public class LeaveBalance {
	private int user_id;
	private int total_leaves;
	private int remaining_leaves;
	public LeaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveBalance(int user_id, int total_leaves, int remaining_leaves) {
		super();
		this.user_id = user_id;
		this.total_leaves = total_leaves;
		this.remaining_leaves = remaining_leaves;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getTotal_leaves() {
		return total_leaves;
	}
	public void setTotal_leaves(int total_leaves) {
		this.total_leaves = total_leaves;
	}
	public int getRemaining_leaves() {
		return remaining_leaves;
	}
	public void setRemaining_leaves(int remaining_leaves) {
		this.remaining_leaves = remaining_leaves;
	}
	@Override
	public String toString() {
		return "LeaveBalance [user_id=" + user_id + ", total_leaves=" + total_leaves + ", remaining_leaves="
				+ remaining_leaves + "]";
	}

}
