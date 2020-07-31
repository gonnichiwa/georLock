package com.springboot.georlock.svc;

import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;


    public List<Enteremp> getEnteremp() throws Exception{
        List<Enteremp> record=recordMapper.getEnterempAll();

        for (int i=0; i<record.size();i++){
            Enteremp emp=new Enteremp();
            emp=record.get(i);
            emp.setIntime(emp.getIntime().substring(0, 4) + "-" + emp.getIntime().substring(4, 6) + "-" + emp.getIntime().substring(6, 8) + " " + emp.getIntime().substring(8, 10) + ":" + emp.getIntime().substring(10, 12));
            record.set(i,emp);
        }
        return record;
    }

    public List<Enteremp> getRecordSearch(Dates dates) throws Exception{
        dates.setStartDate(dates.getStartDate().replace("-","")+"0000");
        dates.setEndDate(dates.getEndDate().replace("-","")+"9999");
        dates.setTextSearch("%"+dates.getTextSearch()+"%");

        if(dates.getStartDate().equals("0000") ){
            dates.setStartDate("000000000000");
        }

        if(dates.getEndDate().equals("9999") || dates.getEndDate().equals("000000009999")){
            dates.setEndDate("999999999999");
        }

        List<Enteremp> record=recordMapper.getSearch(dates);
        Enteremp emp=new Enteremp();
        for (int i=0; i<record.size();i++){
            emp=record.get(i);
            emp.setIntime(emp.getIntime().substring(0, 4) + "-" + emp.getIntime().substring(4, 6) + "-" + emp.getIntime().substring(6, 8) + " " + emp.getIntime().substring(8, 10) + ":" + emp.getIntime().substring(10, 12));
            record.set(i,emp);
        }

        return record;
    }
}
