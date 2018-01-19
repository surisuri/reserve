package com.lkssoft.project.reserve.charge.dao;
 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lkssoft.project.reserve.charge.svc.MedicalChargeVO;
import com.lkssoft.project.reserve.system.dao.AbstractDAO;  

@Repository("MedicalChargeDAO")
public class MedicalChargeDAO extends AbstractDAO{

	/**
	 * 검사항목별 수가를 등록한다.
	 * 
	 * @param scheduelMap
	 * @return Object
	 * @exception Exception 
	 */
	public Object insertMedicalCharge(MedicalChargeVO medicalChargeVO) throws Exception{ 	
	    return insert("MedicalCharge.insertMedicalCharge", medicalChargeVO);	
	}
	
	/**
	 * 검사항목별 수가를 수정한다.
	 * 
	 * @param scheduelMap
	 * @return Object
	 * @exception Exception 
	 */
	public Object updateMedicalCharge(MedicalChargeVO medicalChargeVO) throws Exception{ 	
	    return insert("MedicalCharge.updateMedicalCharge", medicalChargeVO);	
	}
	
	/**
	 * 등록된 검사별 수가목록을 조회한다. 
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectListMedicalCharge() throws Exception{
		List<Map<String, Object>> results = selectList("MedicalCharge.selectListMedicalCharge");
		
		return results;
	}
	
	/**
	 * 선택된 수가를 삭제한다. 
	 * 
	 * @param medicalChargeVO 삭제할 수가정보 
	 * @return Object 
	 * @throws Exception 예외처리
	 */
	public Object deleteMedicalCharge(MedicalChargeVO medicalChargeVO) throws Exception{
		return delete("MedicalCharge.deleteMedicalCharge", medicalChargeVO);
	}
}