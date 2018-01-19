package com.lkssoft.project.reserve.schedule.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lkssoft.project.reserve.schedule.svc.ScheduleSEI;
import com.lkssoft.project.reserve.schedule.svc.ScheduleVO;
import com.lkssoft.project.reserve.system.common.vo.ResultsVO;

@EnableWebMvc
@Controller
public class ScheduleController {
     
	@Autowired
	private ScheduleSEI scheduleSEI;
	
	/**
	 * 스케쥴을 등록/수정한다.
	 * 
	 * @param commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
    @RequestMapping(value="/registerSchedule", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ScheduleVO registerSchedule(HttpServletRequest req, ScheduleVO scheduleVo) throws Exception{
    		ScheduleVO result = new ScheduleVO();
    		try {
	    		if ( StringUtils.isEmpty( scheduleVo.getScheduleId() )) {
	    			scheduleSEI.insertSchedule(scheduleVo);
	    		}else {
	    			scheduleSEI.updateSchedule(scheduleVo);
	    		}
	    		
	    		result.setResult("suc");
	    	}catch(Exception e) {
	    		result.setResult(e.toString());
	    	}

	    	return result;
    }

	/**
	 * 예약을 취소한다.
	 * 
	 * @param commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
    @RequestMapping(value="/cancelSchedule", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ScheduleVO cancelSchedule(HttpServletRequest req, ScheduleVO scheduleVo) throws Exception{
    		ScheduleVO result = new ScheduleVO();
    		try {
    			scheduleSEI.cancelSchedule(scheduleVo);
	    		
	    		result.setResult("suc");
	    	}catch(Exception e) {
	    		result.setResult(e.toString());
	    	}

	    	return result;
    }
    
	/**
	 * 스케쥴을 삭제한다.
	 * 
	 * @param commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
    @RequestMapping(value="/deleteSchedule", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ScheduleVO deleteSchedule(HttpServletRequest req, ScheduleVO scheduleVo) throws Exception{
    		ScheduleVO result = new ScheduleVO();
    		try {
	    		scheduleSEI.deleteSchedule(scheduleVo);
	    		
	    		result.setResult("suc");
	    	}catch(Exception e) {
	    		result.setResult(e.toString());
	    	}

	    	return result;
    }
    
    /**
     * 등록된 event 목록을 조회한다.
     * 
     * @param req httprservletequest 객체 
     * @param startDateTime
     * @param endDateTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/selectListSchedule", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultsVO selectListSchedule(HttpServletRequest req, 
    											  String startDate,
    											  String endDate) throws Exception{
  
      	List<Map<String, Object>> results = new ArrayList<Map<String, Object>>(); 		
    		ResultsVO resultsVo = new ResultsVO();
    		
    		try {
    			
    			ScheduleVO searchVO = new ScheduleVO();
    			searchVO.setStartDate(startDate);
    			searchVO.setEndDate(endDate);
    			results = scheduleSEI.selectListEvent(searchVO);
    			
    			resultsVo.setResult(true);
    			resultsVo.setObjList(results);
    			
    		}catch(Exception e) {
    			resultsVo.setResult(false);
    			resultsVo.setMsg(e.toString());
    		}

    		return resultsVo;
    }
    
	/**
	 * 스케쥴을 일괄등록한다.
	 * 
	 * @param commandMap
	 * @return ModelAndView
	 * @throws Exception
	 */
    @RequestMapping(value="/registerScheduleBatch", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResultsVO registerScheduleBatch(HttpServletRequest req, 
    														 String startDate,
    														 String endDate) throws Exception{
		ResultsVO resultsVo = new ResultsVO();
    		try {
	    		
	    		scheduleSEI.insertBatchSchedule(startDate, endDate);
    			
    			resultsVo.setResult(true);
	    	}catch(Exception e) {
	    		resultsVo.setResult(false);
	    		resultsVo.setMsg(e.toString());
	    	}

	    	return resultsVo;
    }    
}