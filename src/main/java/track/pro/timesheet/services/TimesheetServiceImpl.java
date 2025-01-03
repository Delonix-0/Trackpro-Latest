package track.pro.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.timesheet.entities.Timesheet;
import track.pro.timesheet.repository.TimesheetRepositoryImpl;
import track.pro.timesheet.services.TimesheetService;

@Service
public class TimesheetServiceImpl implements TimesheetService {
	
	@Autowired
	TimesheetRepositoryImpl timesheetRepositoryimpl;

	@Override
	public int fillTimesheet(Timesheet timesheet) {
		return timesheetRepositoryimpl.fillTimesheet(timesheet) ;
	}
	
}
