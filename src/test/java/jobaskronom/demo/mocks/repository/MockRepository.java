package jobaskronom.demo.mocks.repository;

import java.util.ArrayList;
import java.util.Optional;

public abstract class MockRepository <T> {

    ArrayList<T> mockTable = new ArrayList<>();


    public T save(T objectToSave){
        mockTable.add(objectToSave);
        return mockTable.get(mockTable.indexOf(objectToSave));
    }

    public T getByIndex(int index){
        return mockTable.get(index);
    }

    public Boolean checkIfExists(T objectToCheck){
        return mockTable.contains(objectToCheck);
    }

    public void emptyMockTable(){
        mockTable.clear();
    }

}

