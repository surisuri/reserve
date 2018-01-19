package com.lkssoft.project.reserve.schedule.svc.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lkssoft.project.reserve.schedule.dao.ScheduleDAO;
import com.lkssoft.project.reserve.schedule.svc.ScheduleSEI;
import com.lkssoft.project.reserve.schedule.svc.ScheduleVO;

@Service("com.lkssoft.project.schedule.svc.ScheduleSEI")
public class ScheduleSVC implements ScheduleSEI{
	
	/**
	 * schedule정보를 관리하는 DAO
	 */
	@Resource(name="ScheduleDAO")
	private ScheduleDAO scheduleDAO;

	/**
	 * 스케쥴을 수정한다. 
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	@Override
	public Object updateSchedule(ScheduleVO scheduleVo) throws Exception {
		scheduleVo.setUpdateUsrId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		// 환자명을 입력하면 등록으로 판단함
	    if( StringUtils.isEmpty( scheduleVo.getPatientName().trim() ) ) { 
	    		scheduleVo.setEventStatus("01");  // 미등록 
	    }else {
	    		scheduleVo.setEventStatus("02");  // 등록 
		}
		return scheduleDAO.updateSchedule(scheduleVo);
	}

	/**
	 * 예약을 취소한다.
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	@Override
	public Object cancelSchedule(ScheduleVO scheduleVo) throws Exception {
		
	    	scheduleVo.setEventStatus("01");  // 미등록
	    scheduleVo.setPatientName(null);
	    scheduleVo.setPrescriberUsrNm(null);
	    scheduleVo.setSimpleMsgCtnt(null);
	    scheduleVo.setDeleteYn("N");
		scheduleVo.setUpdateUsrId(SecurityContextHolder.getContext().getAuthentication().getName());
	    
		return scheduleDAO.updateSchedule(scheduleVo);
	}
	
	/**
	 * 조회기간 내 등록된 스케쥴 목록을 조회한다.
	 * 
	 * @param searchVO 조회조건 
	 * @return List<ScheduleVO> 일정목
	 * @throws Exception 예외처
	 */
	@Override
	public List<Map<String, Object>> selectListEvent(ScheduleVO searchVO) throws Exception {
		return scheduleDAO.selectListEvent(searchVO);
	}

	/**
	 * 스케쥴을 등록한다. 
	 * 
	 * @param scheduleVo
	 * @return Object
	 * @throws Exceptio 
	 * 
	 */
	@Override
	public Object insertSchedule(ScheduleVO scheduleVo) throws Exception {
		if( StringUtils.isEmpty(scheduleVo.getScheduleId()) ) {
			scheduleVo.setScheduleId(scheduleDAO.nextSequeceNumber());
		}
		
		// 환자명을 입력하면 등록으로 판단함
		String patientName = scheduleVo.getPatientName();
		if( StringUtils.isEmpty(patientName)) {
			scheduleVo.setEventStatus("01");  // 미등록 
		}else {
			scheduleVo.setEventStatus("02");  // 등록 
		}
			
	    scheduleVo.setCreateUsrId( SecurityContextHolder.getContext().getAuthentication().getName() );
	    scheduleVo.setDeleteYn("N");
		
		return scheduleDAO.insertSchedule(scheduleVo);
	}

	/**
	 * 스케쥴을 삭제한다. 
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	@Override
	public Object deleteSchedule(ScheduleVO scheduleVo) throws Exception{
		scheduleVo.setDeleteYn("Y");
		return scheduleDAO.deleteSchedule(scheduleVo);
	}
	
	
	/**
	 * 스케쥴을 일괄 등록한다. 
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Object
	 * @exception Exception 
	 */
	@Override
	public void insertBatchSchedule(String strStartDate, String strEndDate) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(strStartDate);
		Calendar curCal = Calendar.getInstance();
		curCal.setTime(startDate);
		
		Date endDate   = sdf.parse(strEndDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		endCal.add(Calendar.DAY_OF_WEEK, 1);
		
		while( curCal.before(endCal) ) {
			int day = curCal.get(Calendar.DAY_OF_WEEK);
			
			// 스케쥴 기본정보 세팅
			ScheduleVO scheduleVo = new ScheduleVO();
		    scheduleVo.setEventDate( sdf.format(curCal.getTime()) );
		    scheduleVo.setEventStatus("01"); // 미등록
		    scheduleVo.setExamUsrNm("김경미");
		    scheduleVo.setScheduleBatchCreateYn("Y");
			
			if( day == 2 || day == 3 || day == 5 || day == 6 ) {  // mon, tuesday, thursday, friday
				scheduleVo.setEventStartTime("09:00");
				scheduleVo.setEventEndTime("11:00");
				this.insertSchedule(scheduleVo);
				
				scheduleVo.setScheduleId(null);
				scheduleVo.setEventStartTime("11:00");
				scheduleVo.setEventEndTime("13:00");
				this.insertSchedule(scheduleVo);
				
				if( day == 3 || day == 5) {
					scheduleVo.setExamUsrNm("김미현");

					scheduleVo.setScheduleId(null);
					scheduleVo.setEventStartTime("09:30");
					scheduleVo.setEventEndTime("11:00");
					this.insertSchedule(scheduleVo);
					
					scheduleVo.setScheduleId(null);
					scheduleVo.setEventStartTime("11:00");
					scheduleVo.setEventEndTime("12:30");
					this.insertSchedule(scheduleVo);					
				}
				
			}else if(day == 4) {  // wednesday
				scheduleVo.setEventStartTime("10:00");
				scheduleVo.setEventEndTime("12:00");
				this.insertSchedule(scheduleVo);
				
				scheduleVo.setScheduleId(null);
				scheduleVo.setEventStartTime("12:00");
				scheduleVo.setEventEndTime("14:00");
				this.insertSchedule(scheduleVo);
				
			}else {
				// do nothing
			}
			
			curCal.add(Calendar.DAY_OF_WEEK, 1);
		}
		
	}
}