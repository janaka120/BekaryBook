/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.modle;

/**
 *
 * @author janaka
 */
public class ComboboxModule {
    private String key;
    private String value;

    public ComboboxModule() {
    }
    
    public ComboboxModule(String k, String v){
        this.key = k;
        this.value = v;
        System.out.println("key : "+this.key);
        System.out.println("value : "+this.value);
    }
    
    public void setKey(String k){
        this.key = k;
    }
    public String getKey(){
        return key;
    }
    
    public void setValue(String v){
        this.value = v;
    }
    
    public String getValue(){
        return value;
    }
            
}
