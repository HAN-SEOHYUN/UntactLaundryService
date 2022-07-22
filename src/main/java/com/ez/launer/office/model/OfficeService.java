package com.ez.launer.office.model;

import java.util.List;
import java.util.Map;

public interface OfficeService {
    OfficeVO selectByNo(int no);
    List<OfficeVO> selectAll();
    int insertOfficeAdmin(Map<String, Object> map);
    List<Map<String, Object>> selectAllManager();
    Map<String, Object> selectManagerByNo(int no);
    int deleteOfficeAdmin(int usersNo);
    int deleteOffice(int no);
    List<Map<String, Object>> selectOfficeInfo();
    public int insertOffice(OfficeVO vo);
}
