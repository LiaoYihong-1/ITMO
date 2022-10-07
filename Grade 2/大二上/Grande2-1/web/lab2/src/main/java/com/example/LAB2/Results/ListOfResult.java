package com.example.LAB2.Results;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

public class ListOfResult implements Serializable {
    @Getter
    @Setter
    List<Result> results = new ArrayList<>();

    public ListOfResult(){
    }


    public void addResult(Result result){
        this.results.add(result);
    }


}
