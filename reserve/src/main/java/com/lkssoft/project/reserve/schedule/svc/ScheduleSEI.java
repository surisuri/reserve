package com.lkssoft.project.reserve.schedule.svc;

import java.util.List;
import java.util.Map;

public interface ScheduleSEI{
	
	/**
	 * 스케쥴을 등록, 수정한다.
	 * 
	 * @param scheduelMap
	 * @return Object
	 * @exception Exception 
	 */
	public Object insertSchedule(ScheduleVO scheduleVo) throws Exception;
	
	/**
	 * 스케쥴을 수정한다. 
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	public Object updateSchedule(ScheduleVO scheduleVo) throws Exception;
	
	/**
	 * 예약을 취소한다.
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	public Object cancelSchedule(ScheduleVO scheduleVo) throws Exception;

	/**
	 * 스케쥴을 삭제한다. 
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	public Object deleteSchedule(ScheduleVO scheduleVo) throws Exception;
	
	/**
	 * 조회기간 내 등록된 스케쥴 목록을 조회한다.
	 * 
	 * @param searchVO 조회조
	 * @return List<ScheduleVO> 일정목
	 * @throws Exception 예외처
	 */
	public List<Map<String, Object>> selectListEvent(ScheduleVO searchVO) throws Exception;
	
	/**
	 * 스케쥴을 일괄 등록한다. 
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Object
	 * @exception Exception 
	 */
	public void insertBatchSchedule(String startDate, String endDate) throws Exception;	
}
