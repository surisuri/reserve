package com.lkssoft.project.reserve.charge.svc;

import java.util.List;
import java.util.Map;

public interface MedicalChargeSEI{
	
	/**
	 * 스케쥴을 등록, 수정한다.
	 * 
	 * @param medicalChargeVO 검사항목별 수가 정
	 * @return Object
	 * @exception Exception 
	 */
	public Object mergeMedicalCharge(MedicalChargeVO medicalChargeVO) throws Exception;
	
	/**
	 * 등록된 검사별 수가목록을 조회한다. 
	 * 
	 * @param searchVO
	 * @return List<Map<String, Object>> 검사항목별 수가목록 
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectListMedicalCharge() throws Exception;
	
	/**
	 * 선택된 수가를 삭제한다. 
	 * 
	 * @param medicalChargeVO 삭제할 수가정보 
	 * @return Object 
	 * @throws Exception 예외처리
	 */
	public Object deleteMedicalCharge(MedicalChargeVO medicalChargeVO) throws Exception;	
}
