/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.batch.reader;

import com.mazdausa.mmg.business.iface.UserBusIFace;
import com.mazdausa.mmg.db.vo.UserVO;
import java.text.ParseException;
import java.util.List;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This Class is the User Chunk reader which is responsible for reading the list of users present in the database as CHUNKS.
 * @author PankajB
 * @version 1.0
 */
public class UsersChunkReader implements ItemReader, ItemStream {

    @Autowired
    private UserBusIFace userBService;
    int currentIndex = 0,records=1;
    private static final String CURRENT_INDEX = "current.index";


    /**
     * This is the Spring Batch READ Function, which will be invoked every time the read operation is being called.
     * @return
     * @throws Exception
     * @throws UnexpectedInputException
     * @throws ParseException
     */
    public Object read() throws Exception, UnexpectedInputException, ParseException {


        List<UserVO> listOfUsers=userBService.getAllActiveUsers(records, currentIndex);
            
        if (listOfUsers.isEmpty()) {
            return null;
        }
        currentIndex+=records;
        return listOfUsers;

    }

    public void open(ExecutionContext executionContext) throws ItemStreamException {
         if(executionContext.containsKey(CURRENT_INDEX)){
            currentIndex = new Long(executionContext.getLong(CURRENT_INDEX)).intValue();
        }
        else{
            currentIndex = 0;
        }
    }

    public void update(ExecutionContext executionContext) throws ItemStreamException {
      executionContext.putLong(CURRENT_INDEX, new Long(currentIndex).longValue());
    }

    public void close() throws ItemStreamException {
        
    }



    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }


    
}
