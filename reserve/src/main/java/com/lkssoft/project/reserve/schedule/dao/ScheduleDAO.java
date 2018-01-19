package com.lkssoft.project.reserve.schedule.dao;
 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lkssoft.project.reserve.schedule.svc.ScheduleVO;
import com.lkssoft.project.reserve.system.dao.AbstractDAO;  

@Repository("ScheduleDAO")
public class ScheduleDAO extends AbstractDAO{

	/**
	 * 스케쥴을 등록한다.
	 * 
	 * @param scheduelMap
	 * @return Object
	 * @exception Exception 
	 */
	public Object insertSchedule(ScheduleVO scheculeVo) throws Exception{ 	
	    return insert("Schedule.insertSchedule", scheculeVo);	
	}
	
	/**
	 * 스케쥴을 수정한다. 
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	public Object updateSchedule(ScheduleVO scheculeVo) throws Exception{ 	
	    return insert("Schedule.updateSchedule", scheculeVo);	
	}	
	
	/**
	 * 스케쥴을 삭제한다. 
	 * 
	 * @param scheculeVo
	 * @return Object
	 * @throws Exception
	 */
	public Object deleteSchedule(ScheduleVO scheduleVo) throws Exception{ 	
	    return insert("Schedule.deleteSchedule", scheduleVo);	
	}
	
	/**
	 * evnet 일련번호를 생성한다.
	 * 
	 * @return String 일련번
	 * @throws Exception 예외처리
	 */
	public String nextSequeceNumber() throws Exception{
		return (String)selectOne("Schedule.nextSequeceNumber");
	}
	
	/**
	 * 조회기간 내 등록된 스케쥴 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectListEvent(ScheduleVO searchVO) throws Exception{
		List<Map<String, Object>> results = selectList("Schedule.selectListEvent", searchVO);
		
		return results;
	}
}